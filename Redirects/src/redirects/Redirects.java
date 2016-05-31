package redirects;

import javax.swing.JFrame;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import javax.swing.JFileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Redirects extends JFrame {

	public static int oneOrTwo;
	List<String[]> resolvedData;
	static String[][] array;
	String csvFile;
	static int i = 0;
	static int x = 0;
	static int returnCode;
	static String returnURL;
	static intro in = new intro();

	public static void main(String[] args) {

		intro.main(args);

	}

	public void getURLs() throws IOException {
		System.out.println(oneOrTwo);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			csvFile = selectedFile.getAbsolutePath();
		}

		parseURLs();
	}

	public void parseURLs() throws IOException {

		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\n");
		CsvParser parser = new CsvParser(settings);

		try {
			resolvedData = parser.parseAll(new FileReader(csvFile));
			array = new String[resolvedData.size()][];
			resolvedData.toArray(array);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		// Print the array
		for (String[] arr : array) {
			System.out.println(Arrays.toString(arr));

		}

		callURL();
	}

	public static void callURL() throws MalformedURLException, IOException {

		if (oneOrTwo == 1) {
			int arrayLenght = array.length;
			do {
				String currentURL = Arrays.deepToString(array[i]);

				currentURL = currentURL.substring(1, currentURL.length() - 1);
				returnCode = getResponseCode(currentURL);
				rewriteArray(0);
				i++;

			} while (i < arrayLenght);
		}
		if (oneOrTwo == 2) {
			int arrayLenght = array.length;
			do {
				String currentURL = array[x][i].toString();
				returnCode = getResponseCode(currentURL);
				rewriteArray(1);
				i++;
				x++;
			} while (i < arrayLenght);
		}

	}

	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		returnURL = huc.getURL().toString();

		System.out.println(returnURL);
		return huc.getResponseCode();

	}

	public static void rewriteArray(int a) {

		if (oneOrTwo == 2) {
			String t = array[x][i];
			i = i + a;
			String f = t + " WAS REDIRECTED TO " + returnURL + " RETURNED: " + returnCode;
			in.addText(f + System.lineSeparator());
			f = array[x][i];
			System.out.println(f);
		}

		if (oneOrTwo == 1) {
			String t = Arrays.deepToString(array[i]);
			i = i + a;
			String f = t + " WAS REDIRECTED TO " + returnURL + " RETURNED: " + returnCode;
			in.addText(f + System.lineSeparator());
			array[i] = f.split("");
			System.out.println(f);
		}
	}

	public void writeCSV() throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter("C:/push/myfile.csv"));
		br.write(arrayToCsv(array));
		br.close();

	}

	public String arrayToCsv(String[][] h) {
		String csv = "";
		for (int i = 0; i < h.length; i++) {
			for (int j = 0; j < h[i].length; j++) {
				csv = csv + h[i][j]; 
				if (j == (h[i].length - 1))
					csv = csv + ", " + System.lineSeparator();
			}
			if (i == (h.length - 1))
				csv = csv + System.lineSeparator();
		}
		
		System.out.println("DONE");
		return csv;
	}

}
