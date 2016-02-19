import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.JUnitCore;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class FTISocialCrawl {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	List<String[]> resolvedData;
	String[][] array;
	int i = 0;
	String[] errors = new String[7000];
	int c = 0;
	int p = 0;
	String errorTxtFile = "C:\\Users\\kevin.anderson\\Desktop\\errors.txt";
	String csvFile = "C:\\Users\\kevin.anderson\\Desktop\\book1.csv";
	
	
	/* Application takes a one column CSV(csvFile) of URLs and crawls the URLs to verify that an element is present.
	 * It will then output the URLs that were missing the element in the file stored at errorTxtFile.
	 * Incoming CSV is a one column table.  This code cannot handle 2 columns of data at this time. 
	 */
	
	public static void main(String[] args) {
		
		JUnitCore.main("FTISocialCrawl");
	}

	public void writeFile() {
		try {
			PrintWriter writer = new PrintWriter(errorTxtFile, "UTF-8");
			for (int z = 0; z < errors.length; z++) {
				writer.write("\n  " + i + " " + errors[z] + " \n ");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No such file exists.");
		}
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

	}

	@Test
	public void testFTISocialCrawl() throws Exception {

		parseURLs();
		int x = array.length;
		double e;
		double g;
		DecimalFormat df = new DecimalFormat("#.##");
		double t;
		String d;

		do {
			e = x;
			g = i;
			t = 100 * (g / e);
			d = df.format(t);
			String url = array[i][0].toLowerCase();
			
			if (url.contains("pdf") == true || url.contains("vcf") == true || url.contains("jpg") == true
					|| url.contains("jpeg") == true) {
				
				i++;
			}

			else {

				driver.get(url);

				if (driver.getCurrentUrl().contains("404") == true) {
					i++;
				}

				else {
					try {

						assertEquals("", driver.findElement(By.cssSelector("a.icon-linkedin")).getText());
						assertEquals("", driver.findElement(By.cssSelector("a.icon-twitter")).getText());
						assertEquals("", driver.findElement(By.cssSelector("a.icon-facebook")).getText());
						
						
					} catch (NoSuchElementException ex) {
						errors[p] = url + "\n";
						p++;
						System.out.println(i + ") " + url + " - " + d + "%" + " Hit Error");
					}
					i++;
				}
			}
		} while (i < x);

		writeFile();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
