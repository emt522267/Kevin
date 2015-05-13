package Test.testredirects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	int b = 0;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Test
	public void test() throws Exception {
		
			
			
			String fails[] = new String[1000];
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System
					.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(null);
			String file;
			File selectedFile = null;

			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: "
						+ selectedFile.getAbsolutePath());
			} else {
				JOptionPane.showMessageDialog(null, "Error",
						"Please choose a file", JOptionPane.ERROR_MESSAGE);
			}

			file = selectedFile.getAbsolutePath();

			BufferedReader CSVFile = new BufferedReader(new FileReader(file));

			String dataRow = CSVFile.readLine();

			int i = 0;

			while (dataRow != null) {

				String[] dataArray = dataRow.split(",");
				for (String item : dataArray) {

					URL url = new URL(item);
					
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();
					i++;
					int code = connection.getResponseCode();

					System.out.print(item + " " + code + "\n");

					if (code != 200) {
						fails[i] = System.lineSeparator() + item + " " + code + System.lineSeparator();
					}
				}

				// driver.get(item); // Print the data line.
				dataRow = CSVFile.readLine();

			}

			

			CSVFile.close();

			BufferedWriter outputWriter = null;
			  outputWriter = new BufferedWriter(new FileWriter("c://results.txt"));
			  outputWriter.write(Arrays.toString(fails));
			  outputWriter.flush();  
			  outputWriter.close();

		

	}

	@After
	public void tearDown() throws Exception {
		System.out.println(b + " Failed");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
