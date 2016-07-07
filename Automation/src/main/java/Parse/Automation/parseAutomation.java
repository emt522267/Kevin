package Parse.Automation;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.JUnitCore;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class parseAutomation {

	static String version = "V2.2.2";
	String parseUser = "jake.brokaw@lpga.com";
	String parsePassword = "ChangX31";
	private WebDriver driver;
	private WebDriver driver2;
	private String baseUrl = "http://lpga.com/leaderboard";
	private StringBuffer verificationErrors = new StringBuffer();
	String Player;
	String Time;
	String round;
	int tr;
	String tableData[] = new String[4000];
	int i;
	String deliveryDate;
	int a = 7;
	int d = 5;
	int f = 1;
	String tournamentName;
	String errors[] = new String[1000];
	boolean finish = false;
	String errorTest;
	String[] timeZones = TimeZone.getAvailableIDs();
	String tournamentOffset;
	String tearApart;
	String Hour;
	String Minute;
	String minute;
	String aMpM;
	long starts;
	String time;
	String percentString;
	JProgressBar progressBar = new JProgressBar();
	JFrame pf = new JFrame("Progress");
	String errorex = "";
	String PCleaderboardFile = "c:/PusH/Leaderboard.txt";
	String PCerrorFile = "c:/PusH/LPGA Errors.txt";
	String PCstaticHTML = "C:/PusH/staticHTML.html";
	String PCstaticHTMLURL = "file:///C:/PusH/staticHTML.html";
	String PCdebugFile = "";
	String homeDir;
	String MACleaderboardFile = "";
	String MACerrorFile = "";
	String MACstaticHTML = "";
	String MACstaticHTMLURL = "";
	String MACdebugFile = "";
	String PC;
	int daFuq = 0;

	public static void main(String[] args) {

		// SplashDemo splash = new SplashDemo();

		JUnitCore.main("Parse.Automation.parseAutomation");
	}

	@Before
	public void setUp() throws Exception {

		// SplashDemo.splashRun();

		getOS();

		chromeDriverSetup();

	}

	public void chromeDriverSetup() {
		if (PC.equalsIgnoreCase("Mac") == true) {
			String chromeDriver = System.getProperty("user.home");
			chromeDriver = chromeDriver + "/Desktop/PusH/chromedriver";
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			driver = new ChromeDriver();
			driver2 = new ChromeDriver();
			baseUrl = "http://lpga.com/leaderboard";
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			setMacVariables();

		} else {

			System.setProperty("webdriver.chrome.driver", "C:\\PusH\\chromedriver_win32\\chromedriver.exe");

			driver = new ChromeDriver();
			driver2 = new ChromeDriver();
			baseUrl = "file:///C:/Users/kevin.anderson/Desktop/staticHTML.html";
			// "http://lpga.com/leaderboard";

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}

	public void setMacVariables() {
		homeDir = System.getProperty("user.home");
		homeDir = homeDir + "/Desktop/";
		homeDir.replace("//", "/");
		MACleaderboardFile = homeDir + "PusH/Leaderboard.txt";
		MACerrorFile = homeDir + "PusH/LPGA Errors.txt";
		MACstaticHTML = homeDir + "PusH/staticHTML.html";
		MACstaticHTMLURL = "file:///" + homeDir + "PusH/staticHTML.html";
		MACdebugFile = "";
	}

	public void getOS() {

		String OS = System.getProperty("os.name");
		System.out.println(System.getProperty("os.name"));
		if (OS.contains("Windows") == true) {
			PC = "PC";
		} else {
			PC = "MAC";
		}
	}

	@Test
	public void test() throws Exception {

		System.out.println(version + "\nGet Ready to be Parsified!!!\n");
		errorTest = "Test";
		round = JOptionPane.showInputDialog("Please Enter Round");
		deliveryDate = JOptionPane
				.showInputDialog("Please enter the date to deliver the notifications.\nUse YYYY/MM/DD format.");

		if (round.equalsIgnoreCase("debug") == true) {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(null);

			File selectedFile = null;

			if (result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			} else {
				JOptionPane.showMessageDialog(null, "Error", "Please choose a file", JOptionPane.ERROR_MESSAGE);
			}

			if (PC.equalsIgnoreCase("PC")) {
				PCdebugFile = selectedFile.getAbsolutePath();
			} else {
				MACdebugFile = selectedFile.getAbsolutePath();
			}

			round = JOptionPane.showInputDialog("DEBUG MODE:  Please Enter Correct Round");
		}

		if (round.equalsIgnoreCase("1") == true) {
			tournamentName = JOptionPane.showInputDialog(
					"Please enter the tournament name \n(i.e.- Sontaran Classic presented by Gallifrey Bank).");
		}

		tournamentOffset = (String) JOptionPane.showInputDialog(null,
				"Please selct the time zone the tournament is being played in.", "Timezone", JOptionPane.PLAIN_MESSAGE,
				null, timeZones, "");
		progress(0);

		leaderBoard();
		removeBlankEntries();

		do {
			checkForWD();
			Parse(getPlayer(), round, Hour(), Minute(), AMPM());
		}

		while (i <= tableData.length + 1);

	}

	public void writeLeaderboard() throws FileNotFoundException, UnsupportedEncodingException {

		if (PC.equalsIgnoreCase("PC")) {

			try {

				PrintWriter writer = new PrintWriter(PCleaderboardFile, "UTF-8");
				for (int z = 0; z < tableData.length; z++) {
					writer.write("\n  " + i + " " + tableData[z] + " \n ");
				}
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(PCleaderboardFile);
			}
		} else {
			try {

				PrintWriter writer = new PrintWriter(MACleaderboardFile, "UTF-8");
				for (int z = 0; z < tableData.length; z++) {
					writer.write("\n  " + i + " " + tableData[z] + " \n ");
				}
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(MACleaderboardFile);
			}
		}
	}

	public void writeErrors() throws FileNotFoundException, UnsupportedEncodingException {

		if (PC.equalsIgnoreCase("PC")) {
			try {
				PrintWriter writer = new PrintWriter(PCerrorFile, "UTF-8");
				for (int u = 0; u < errors.length; u++) {
					writer.write(errors[u] + " \n " + errorTest);
				}
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(PCerrorFile);
			}
		} else {
			try {
				PrintWriter writer = new PrintWriter(MACerrorFile, "UTF-8");
				for (int u = 0; u < errors.length; u++) {
					writer.write(errors[u] + " \n " + errorTest);
				}
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(MACerrorFile);
			}
		}
	}

	public String setEST(String dateTime) throws ParseException {
		String EST = "";

		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm aa");
		formatter.setTimeZone(TimeZone.getTimeZone(tournamentOffset));
		java.util.Date date = new Date(1);
		String dateString = dateTime;

		date = formatter.parse(dateString);
		formatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		EST = formatter.format(date);

		return EST;
	}

	public String getPlayerURL(String JSON) throws JSONException {
		JSONObject obj = new JSONObject(JSON);
		JSONArray playerArray = obj.getJSONArray("data");
		String playerID = "";
		errorTest = "getplayeURL";
		for (int q = 0; q < playerArray.length();) {
			playerID = playerArray.getJSONObject(q).getString("OverviewUrl");
			break;
		}
		return playerID;
	}

	public String getPlayerID(String playerName) throws JSONException {
		String text;
		String text1;
		String text2;
		errorTest = "getplayerid";

		if (playerName.startsWith("Brooke M") == true) {
			playerName = "Brooke Henderson";
		}
	
		driver2.get("http://origin-www.lpga.com/-/ajax/PlayerSearch/Search?text=" + playerName);
		text = driver2.findElement(By.tagName("body")).getText();
		text1 = getPlayerURL(text);

		int lenght = playerName.length();
		int beginSubString = (10 + lenght);
		int endSubString = beginSubString + 5;

		text2 = text1.substring(beginSubString, endSubString);

		System.out.println("   Player ID: " + text2);

		return text2;
	}

	public String Position() {

		errorTest = "position";

		if (round.equals("1") == true) {

			if (i == 3) {
				f = 0;
			} else {
				f = f + 4;
			}
		}

		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				f = 0;
			} else {
				f = f + 10;
			}
		}

		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				f = 0;
			} else {
				f = f + 11;
			}
		}

		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				f = 0;
			} else {
				f = f + 12;
			}
		}

		String position = tableData[f];
		System.out.println("Position: " + position);

		return position;
	}

	public String getPlayer() {
		errorTest = "getplayer";
		if (round.equals("1") == true) {

			if (i == 3) {
				d = 2;
			} else {
				d = d + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				d = 4;
			} else {
				d = d + 10;
			}
		}

		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				d = 4;
			} else {
				d = d + 11;
			}
		}

		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				d = 4;
			} else {
				d = d + 12;
			}
		}

		System.out.println("Percent Complete: " + percentComplete());
		String player = tableData[d];
		return player;
	}

	public String percentComplete() {
		float divisor = tableData.length;
		float numerator = d;

		float percent = numerator / divisor;

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		percent = percent * 100;

		percentString = df.format(percent);

		String status = percentString + "% Complete";

		DecimalFormat fd = new DecimalFormat();
		fd.setMaximumFractionDigits(0);
		String p = fd.format(percent);
		progress(Integer.parseInt(p));

		return status;
	}

	public void progress(int percentBar) {

		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pf.setTitle("Version: " + version);
		Container content = pf.getContentPane();
		progressBar.setValue(percentBar);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Status Bar will begin once the leaderboard has been read.");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);
		pf.setSize(300, 100);
		pf.setVisible(true);
	}

	public String time() {

		if (round.equals("1") == true) {

			if (i == 3) {
				a = 3;
			} else {
				a = a + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				a = 6;
			} else {
				a = a + 10;
			}
		}
		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				a = 6;
			} else {
				a = a + 11;
			}
		}
		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				a = 6;
			} else {
				a = a + 12;
			}
		}

		minute = tableData[a];

		System.out.println("Time: " + minute);

		if (minute.startsWith("1:") == true || minute.startsWith("2:") == true || minute.startsWith("3:") == true
				|| minute.startsWith("4:") == true || minute.startsWith("5:") == true || minute.startsWith("6:") == true
				|| minute.startsWith("7:") == true || minute.startsWith("8:") == true
				|| minute.startsWith("9:") == true) {
			minute = "0" + minute;
		}

		return minute;
	}

	public String Hour() {

		errorTest = "hour";

		time();

		String hour1 = minute.substring(0, 2);

		hour1 = hour1.replaceFirst("^0+(?!$)", "");

		if (hour1.startsWith("0") == true) {
			hour1 = hour1.replaceFirst("^0+(?!$)", "");
		}

		System.out.println("Hour: " + hour1);

		return hour1;

	}

	public String Minute() {

		errorTest = "minute";

		String minute1 = minute.substring(3, 5);

		System.out.println("Minute: " + minute1);

		return minute1;
	}

	public String AMPM() {

		errorTest = "ampm";

		String pmam = minute.substring(6, 8);

		System.out.println("AM/PM: " + pmam);

		return pmam;
	}

	public void checkForWD() throws Exception {
		errorTest = "checkforwd";

		String position = Position();

		if (position.equalsIgnoreCase("wdc") == true || position.equalsIgnoreCase("cut") == true
				|| position.equalsIgnoreCase("wd") == true || position.equalsIgnoreCase("dq") == true) {
			finish = true;
			tearDown();
		}
	}

	public void leaderBoard() throws InterruptedException, UnsupportedEncodingException {

		if (PC.equalsIgnoreCase("PC")) {

			if (PCdebugFile.contains("htm") == false) {
				driver.get(baseUrl);
			} else {
				driver.get("file:///" + PCdebugFile);
			}

			String html = driver.getPageSource();

			try {
				PrintWriter writer = new PrintWriter(PCstaticHTML);
				writer.write(html);
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(PCstaticHTML);
			}

			driver.get(PCstaticHTMLURL);
			if (driver.getCurrentUrl().contains("lpga") == false)
				errorTest = "leaderboard";

			{

				Thread.sleep(1000);
				WebElement table = driver.findElement(By.className("live-leaderboard"));
				Thread.sleep(500);
				List<WebElement> allRows = table.findElements(By.tagName("TR"));

				for (WebElement row : allRows) {
					List<WebElement> cells = row.findElements(By.tagName("TD"));
					for (WebElement cell : cells) {
						String a = cell.getText().toString();
						tableData[i] = a;

						System.out.println(i + " " + tableData[i]);
						i++;
					}
				}
			}
		} else {
			if (MACdebugFile.contains("htm") == false) {
				driver.get(baseUrl);
			} else {
				driver.get("file:///" + MACdebugFile);
			}

			String html = driver.getPageSource();

			try {
				PrintWriter writer = new PrintWriter(MACstaticHTML);
				writer.write(html);
				writer.close();
			} catch (FileNotFoundException fnf) {
				@SuppressWarnings("unused")
				File file = new File(MACstaticHTML);
			}

			driver.get(MACstaticHTMLURL);
			if (driver.getCurrentUrl().contains("lpga") == false)
				errorTest = "leaderboard";

			{

				Thread.sleep(1000);
				WebElement table = driver.findElement(By.className("live-leaderboard"));
				Thread.sleep(500);
				List<WebElement> allRows = table.findElements(By.tagName("TR"));

				for (WebElement row : allRows) {
					List<WebElement> cells = row.findElements(By.tagName("TD"));
					for (WebElement cell : cells) {
						String a = cell.getText().toString();
						tableData[i] = a;

						System.out.println(i + " " + tableData[i]);
						i++;
					}
				}
			}
		}
	}

	public void removeBlankEntries() throws FileNotFoundException, UnsupportedEncodingException {
		errorTest = "removeblank";
		List<String> list = new ArrayList<String>();

		for (String s : tableData) {
			if (s != null && s.length() > 0) {
				if (s.contains("<BODY") == true || s.contains("Cut Line") == true
						|| s.contains("My Leaderboard") == true || s.contains("Full Leaderboard")
						|| s.equalsIgnoreCase("Add") == true || s.contains("&") == true) {
				} else {
					list.add(s);
				}
			}
		}

		tableData = list.toArray(new String[list.size()]);

		System.out.print(Arrays.toString(tableData));

		i = 3;
		writeLeaderboard();
	}

	public void takeApartNewDate() {

		deliveryDate = tearApart.substring(0, 10);
		deliveryDate = deliveryDate.trim();
		// System.out.println(deliveryDate);

		Hour = tearApart.substring(11, 13);
		if (Hour.startsWith("0")) {
			Hour = Hour.replaceFirst("^0+(?!$)", "");
		}
		Hour = Hour.trim();
		// System.out.println(Hour);

		Minute = tearApart.substring(14, 16);
		Minute = Minute.trim();
		// System.out.println(Minute);

		aMpM = tearApart.substring(17, 19);
		aMpM = aMpM.trim();
		// System.out.println(aMpM);
	}

	public void parseLogin() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\PusH\\screenshot" + i + ".png"));

		driver.manage().deleteAllCookies();
		driver.get("https://www.parse.com/user_session/new");

		driver.findElement(By.name("user_session[email]")).sendKeys(parseUser);
		driver.findElement(By.name("user_session[password]")).clear();
		driver.findElement(By.name("user_session[password]")).sendKeys(parsePassword);
		driver.findElement(By.className("submit__AiNYw")).click();

		daFuq++;

	}

	public void Parse(String player, String Round, String hour, String min, String AM)
			throws AWTException, InterruptedException, ParseException {

		errorTest = "parse";
		String conDate = deliveryDate + " " + hour + ":" + min + " " + AM;
		tearApart = setEST(conDate);

		takeApartNewDate();

		System.out.print("Player: " + player + "    Round: " + Round + "  Time:  " + hour + min + AM);
		try {

			if (daFuq == 0) {

				parseLogin();

			}

			else {

				try {
					driver.findElement(By.linkText("Continue")).click();
				} catch (Exception ex) {

				}

				driver.navigate().to("https://www.parse.com/apps/lpga-now/push_notifications");
				Thread.sleep(2000);
				driver.findElement(By.linkText("Send a push")).click();
				driver.findElement(By.className("icon_windows")).click();

				// driver.findElement(By.id("target_type_segment")).click();
				driver.findElement(By.cssSelector("button.add_constraint_button")).click();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("input.default")).click();
				driver.findElement(By.cssSelector("input.default")).clear();
				driver.findElement(By.cssSelector("input.default")).sendKeys(Keys.DELETE);
				driver.findElement(By.cssSelector("input.default"))
						.sendKeys("Player-" + getPlayerID(player) + Keys.ARROW_DOWN + Keys.ENTER);
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("i.icon_hourGlass")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("push_date")).click();
				driver.findElement(By.name("push_date")).clear();
				driver.findElement(By.name("push_date")).sendKeys(deliveryDate);
				Thread.sleep(3000);
				new Select(driver.findElement(By.name("push_hour"))).selectByVisibleText(Hour);
				new Select(driver.findElement(By.name("push_minute"))).selectByVisibleText(":" + Minute);
				new Select(driver.findElement(By.name("push_ampm"))).selectByVisibleText(aMpM);
				Thread.sleep(1500);
				driver.findElement(By.id("message_all")).clear();

				if (round.equalsIgnoreCase("1")) {
					driver.findElement(By.id("message_all")).sendKeys(player + " is teeing off for Round " + Round
							+ " of the " + tournamentName + ". Follow her scores on LPGA.com or the LPGA Now app.");
				} else {
					driver.findElement(By.id("message_all")).sendKeys(player + " is teeing off for Round " + Round
							+ ". Follow her scores on LPGA.com or the LPGA Now app.");
				}
				Thread.sleep(2000);

				if (driver.findElement(By.id("recipients_counter")).equals("This will be sent to 0 devices") == true) {
					errors[i] = "\n" + player + "\n     ";
					driver.navigate().back();

				} else {
					Thread.sleep(2000);
					driver.findElement(By.id("send_push")).click();

				}
			}

		} catch (NoSuchElementException w) {

			errors[i] = "\n" + player + "\n     " + w.toString();

		}

		catch (ArrayIndexOutOfBoundsException oob) {
			errorex = "Success! Please review Parse entries.";
		}

		catch (Exception ex) {
			errors[i] = "\n" + player + "\n     " + ex.toString();
		}

		i++;

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		driver2.quit();
		writeErrors();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		if (i >= tableData.length || finish == true) {
			JOptionPane.showMessageDialog(null, "Completed " + a + " of " + tableData.length + " " + percentComplete()
					+ ". " + ".  \nThank you for choosing PusH");
		} else {

			if (errorex == "") {
				JOptionPane.showMessageDialog(null, "Completed " + a + " of " + tableData.length + "\n"
						+ percentComplete() + ".  \nThank you for choosing PusH");
			} else {
				JOptionPane.showMessageDialog(null, "Completed " + a + " of " + tableData.length + " "
						+ percentComplete() + ". " + ".  \nThank you for choosing PusH");
			}
		}

	}
}