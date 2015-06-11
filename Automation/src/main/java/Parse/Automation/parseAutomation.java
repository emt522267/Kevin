package Parse.Automation;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class parseAutomation {

	private WebDriver driver;
	private WebDriver driver2;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	String Player;
	String Time;
	String round;
	int tr;
	String tableData[] = new String[4000];
	int i;
	String deliveryDate;
	int a = 7;
	int b = 7;
	int c = 7;
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
	String aMpM;
	long starts;
	String time;
	String percentString;
	JProgressBar progressBar = new JProgressBar();
	JFrame pf = new JFrame("Progress");
	

	public static void main(String[] args) {
		JUnitCore.main("Parse.Automation.parseAutomation");
	}

	@Before
	public void setUp() throws Exception {

		driver = new FirefoxDriver();
		driver2 = new FirefoxDriver();
		baseUrl = "//Leaderboard _ LPGA.html";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void test() throws Exception {
		
	
		
		System.out.println("Get Ready to be Parsified!!!");
		errorTest = "Test";
		round = JOptionPane.showInputDialog("Please Enter Round");
		deliveryDate = JOptionPane
				.showInputDialog("Please enter the date to deliver the notifications.\nUse YYYY/MM/DD format.");

		if (round.equalsIgnoreCase("1") == true) {
			tournamentName = JOptionPane
					.showInputDialog("Please enter the tournament name \n(i.e.- Sontaran Classic presented by Gallifrey Bank).");
		}

		tournamentOffset = (String) JOptionPane
				.showInputDialog(
						null,
						"Please selct the time zone the tournament is being played in.",
						"Timezone", JOptionPane.PLAIN_MESSAGE, null, timeZones,
						"");
		progress(0);
		
		leaderBoard();
		removeBlankEntries();

		do {
			checkForWD();
			Parse(getPlayer(), round, Hour(), Minute(), AMPM());
		}

		while (i <= tableData.length + 1);

	}

	public void writeLeaderboard() throws FileNotFoundException,
			UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("c:/LPGA/Leaderboard.txt", "UTF-8");
		for (int z = 0; z < tableData.length; z++) {
			writer.write("\n  " + i + " " + tableData[z] + " \n ");
		}
		writer.close();
	}

	public void writeErrors() throws FileNotFoundException,
			UnsupportedEncodingException {

		PrintWriter writer = new PrintWriter("c:/LPGA/LPGA Errors.txt", "UTF-8");
		for (int u = 0; u < errors.length; u++) {
			writer.write(errors[u] + " \n " + errorTest);
		}
		writer.close();

	}

	public String setEST(String dateTime) throws ParseException {
		String EST = "";
		// try {

		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm aa");
		formatter.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		java.util.Date date = new Date(1);
		String dateString = dateTime;

		date = formatter.parse(dateString);
		formatter.setTimeZone(TimeZone.getTimeZone(tournamentOffset));
		EST = formatter.format(date);

		/*
		 * } catch (Exception e) { errors[i] = e.toString(); }
		 */
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
		driver2.get("http://origin-www.lpga.com/-/ajax/PlayerSearch/Search?text="
				+ playerName);
		text = driver2.findElement(By.tagName("body")).getText();
		text1 = getPlayerURL(text);

		int lenght = playerName.length();
		int beginSubString = (10 + lenght);
		int endSubString = beginSubString + 5;

		text2 = text1.substring(beginSubString, endSubString);

		System.out.println(text2);

		return text2;
	}

	public String Position() {

		errorTest = "position";
		if (round.equals("1") == true) {

			if (i == 3) {
				f = 1;
			} else {
				f = f + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				f = 1;
			} else {
				f = f + 10;
			}
		}

		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				f = 1;
			} else {
				f = f + 11;
			}
		}

		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				f = 1;
			} else {
				f = f + 12;
			}
		}

		String position = tableData[f];
		System.out.println(position);
		return position;
	}

	public String getPlayer() {
		errorTest = "getplayer";
		if (round.equals("1") == true) {

			if (i == 3) {
				d = 3;
			} else {
				d = d + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				d = 5;
			} else {
				d = d + 10;
			}
		}

		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				d = 5;
			} else {
				d = d + 11;
			}
		}

		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				d = 5;
			} else {
				d = d + 12;
			}
		}

		System.out.println(percentComplete());
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
		
		//JProgressBar progressBar = new JProgressBar();
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = pf.getContentPane();
		
		progressBar.setValue(percentBar);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Status Bar will begin once leaderboard has been read.");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);
		pf.setSize(300, 100);
		pf.setVisible(true);
	}

	public String Hour() {
		String hour;
		errorTest = "hour";

		if (round.equals("1") == true) {

			if (i == 3) {
				a = 4;
			} else {
				a = a + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				a = 7;
			} else {
				a = a + 10;
			}
		}
		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				a = 7;
			} else {
				a = a + 11;
			}
		}
		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				a = 7;
			} else {
				a = a + 12;
			}
		}

		hour = tableData[a];

		if (hour.endsWith("*")) {
			hour = hour.replace("*", "");
		}

		if (hour.startsWith("1:") || hour.startsWith("2:")
				|| hour.startsWith("3:") || hour.startsWith("4:")
				|| hour.startsWith("5:") || hour.startsWith("6:")
				|| hour.startsWith("7:") || hour.startsWith("8:")
				|| hour.startsWith("9:")) {
			hour = "0" + hour;
		}

		String hour1 = hour.substring(0, 2);

		hour1 = hour1.replaceFirst("^0+(?!$)", "");

		if (hour1.startsWith("0")) {
			hour1 = hour1.replaceFirst("^0+(?!$)", "");
		}

		return hour1;
	}

	public String Minute() {
		String minute;
		errorTest = "minute";
		if (round.equals("1") == true) {

			if (i == 3) {
				b = 4;
			} else {
				b = b + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				b = 7;
			} else {
				b = b + 10;
			}
		}
		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				b = 7;
			} else {
				b = b + 11;
			}
		}
		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				b = 7;
			} else {
				b = b + 12;
			}
		}

		minute = tableData[b];

		if (minute.startsWith("1:") || minute.startsWith("2:")
				|| minute.startsWith("3:") || minute.startsWith("4:")
				|| minute.startsWith("5:") || minute.startsWith("6:")
				|| minute.startsWith("7:") || minute.startsWith("8:")
				|| minute.startsWith("9:")) {
			minute = "0" + minute;
		}

		minute = minute.substring(3, 5);

		return minute;
	}

	public String AMPM() {
		String ampm;
		String pmam = "";
		errorTest = "ampm";

		if (round.equals("1") == true) {

			if (i == 3) {
				c = 4;
			} else {
				c = c + 5;
			}
		}
		if (round.equalsIgnoreCase("2") == true) {
			if (i == 3) {
				c = 7;
			} else {
				c = c + 10;
			}
		}
		if (round.equalsIgnoreCase("3") == true) {
			if (i == 3) {
				c = 7;
			} else {
				c = c + 11;
			}
		}
		if (round.equalsIgnoreCase("4") == true) {
			if (i == 3) {
				c = 7;
			} else {
				c = c + 12;
			}
		}

		ampm = tableData[c];

		System.out.println(tableData[c]);

		if (ampm.startsWith("1:") == true || ampm.startsWith("2:") == true
				|| ampm.startsWith("3:") == true
				|| ampm.startsWith("4:") == true
				|| ampm.startsWith("5:") == true
				|| ampm.startsWith("6:") == true
				|| ampm.startsWith("7:") == true
				|| ampm.startsWith("8:") == true
				|| ampm.startsWith("9:") == true) {
			ampm = "0" + ampm;

		}
		pmam = ampm.substring(6, 8);

		return pmam;
	}

	public void checkForWD() throws Exception {
		errorTest = "checkforwd";

		String position = Position();

		if (position.equalsIgnoreCase("wdc") == true
				|| position.equalsIgnoreCase("cut") == true
				|| position.equalsIgnoreCase("wd") == true
				|| position.equalsIgnoreCase("dq") == true) {
			finish = true;
			tearDown();
		}
	}

	public void leaderBoard() throws InterruptedException {
		if (driver.getCurrentUrl().contains("lpga") == false)
			errorTest = "leaderboard";

		{
			// driver.get(baseUrl + "/leaderboard");
			Thread.sleep(1000);
			WebElement table = driver.findElement(By
					.className("live-leaderboard"));
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

	public void removeBlankEntries() throws FileNotFoundException,
			UnsupportedEncodingException {
		errorTest = "removeblank";
		List<String> list = new ArrayList<String>();

		for (String s : tableData) {
			if (s != null && s.length() > 0) {
				if (s.contains("<BODY") == true || s.contains("Cut Line")) {
				} else {
					list.add(s);
				}
			}
		}

		tableData = list.toArray(new String[list.size()]);
		i = 3;
		writeLeaderboard();
	}

	public void takeApartNewDate() {

		deliveryDate = tearApart.substring(0, 10);
		deliveryDate = deliveryDate.trim();
		System.out.println(deliveryDate);

		Hour = tearApart.substring(11, 13);
		if (Hour.startsWith("0")) {
			Hour = Hour.replaceFirst("^0+(?!$)", "");
		}
		Hour = Hour.trim();
		System.out.println(Hour);

		Minute = tearApart.substring(14, 16);
		Minute = Minute.trim();
		System.out.println(Minute);

		aMpM = tearApart.substring(17, 19);
		aMpM = aMpM.trim();
		System.out.println(aMpM);
	}

	public void Parse(String player, String Round, String hour, String min,
			String AM) throws AWTException, InterruptedException,
			ParseException {
		errorTest = "parse";

		String conDate = deliveryDate + " " + hour + ":" + min + " " + AM;
		System.out.println(player);

		tearApart = setEST(conDate);

		takeApartNewDate();

		try {

			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("c:\\LPGA\\screenshot" + i
					+ ".png"));

			driver.manage().deleteAllCookies();
			driver.get("https://www.parse.com/user_session/new");

			driver.findElement(By.id("user_session_email")).sendKeys(
					"jake.brokaw@lpga.com");
			driver.findElement(By.id("user_session_password")).clear();
			driver.findElement(By.id("user_session_password")).sendKeys(
					"ChangX31");
			driver.findElement(By.className("submit")).click();

			driver.navigate().to(
					"https://www.parse.com/apps/lpga-now/push_notifications");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a.buttonSend.ui_button > span"))
					.click();
			driver.findElement(By.id("target_type_segment")).click();
			driver.findElement(By.cssSelector("button.add_constraint_button"))
					.click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input.default")).click();
			driver.findElement(By.cssSelector("input.default")).clear();
			driver.findElement(By.cssSelector("input.default")).sendKeys(
					Keys.DELETE);
			driver.findElement(By.cssSelector("input.default")).sendKeys(
					"Player-" + getPlayerID(player) + Keys.ARROW_DOWN
							+ Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("i.icon_hourGlass")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("push_date")).click();
			driver.findElement(By.name("push_date")).clear();
			driver.findElement(By.name("push_date")).sendKeys(deliveryDate);
			Thread.sleep(3000);
			new Select(driver.findElement(By.name("push_hour")))
					.selectByVisibleText(Hour);
			new Select(driver.findElement(By.name("push_minute")))
					.selectByVisibleText(":" + Minute);
			new Select(driver.findElement(By.name("push_ampm")))
					.selectByVisibleText(aMpM);
			Thread.sleep(1500);
			driver.findElement(By.id("message_all")).clear();

			if (round.equalsIgnoreCase("1")) {
				driver.findElement(By.id("message_all"))
						.sendKeys(
								player
										+ " is teeing off for Round "
										+ Round
										+ " of the "
										+ tournamentName
										+ ". Follow her scores on LPGA.com or the LPGA Now app.");
			} else {
				driver.findElement(By.id("message_all"))
						.sendKeys(
								player
										+ " is teeing off for Round "
										+ Round
										+ ". Follow her scores on LPGA.com or the LPGA Now app.");
			}
			Thread.sleep(2000);

			if (driver.findElement(By.id("recipients_counter")).equals(
					"This will be sent to 0 devices") == true) {
				errors[i] = "\n" + player + "\n     ";
				driver.navigate().back();

			} else {
				Thread.sleep(2000);
				driver.findElement(By.id("send_push")).click();

			}

		} catch (NoSuchElementException w) {

			errors[i] = "\n" + player + "\n     " + w.toString();

		} catch (Exception ex) {
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
			JOptionPane.showMessageDialog(null,
					"Success! Please review Parse entries."
							+ "\nTime to complete: " + time);
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Error occured.  Please vist C:\\LPGA Errors.txt  "
									+ "\nIf this file does not exist, please create it and next time you will have an error log."
									+ "\nTime to complete: " + time);
		}

	}
}