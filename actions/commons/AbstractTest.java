package commons;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	private WebDriver driver;
	//chỉ cho class ke thua AbstractTest su dung
	protected final Log log;

	@BeforeSuite
	private void deleteAllFilesInReportNGScreenshot() {
		System.out.println("---------- START delete file in folder ---------- ");
		deleteAllFileInFolder();
		System.out.println("---------- END delete file in folder ----------");
	}

	private void deleteAllFileInFolder() {
		try {
			String workingDir = System.getProperty("user.dir");
			String pathFolderDownload = workingDir + "\\ReportNGScreenshots";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

		
	protected AbstractTest () {
		log = LogFactory.getLog(getClass());
	}
	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
		
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG_FOLDER+"\\Firefox"+getDateTime()+".log");
			driver = new FirefoxDriver(options);
			//add extension 
//			FirefoxProfile profile= new FirefoxProfile();
//			File translate = new File(GlobalConstants.BROWSER_EXTENSIONS_FOLDER+"\\to_google_translate-4.0.6-fx.xpi");
//			profile.addExtension(translate);
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(profile);
//			driver = new FirefoxDriver(options);
			
			
		} else if (browserName.equalsIgnoreCase("firefox_headless")) {
			FirefoxOptions options= new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
			
		} else if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
		
			driver = new ChromeDriver(options);
			//add extendsion for chrome
//			File file = new File(GlobalConstants.BROWSER_EXTENSIONS_FOLDER+"\\extension_2_0_9_0.crx");
//			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(file);
//			driver = new ChromeDriver(options);
			
		} else if (browserName.equalsIgnoreCase("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver =new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();

		} else {
			System.out.print("Browser not found");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.ADMIN_WORD_PRESS_URL);
		return driver;
	}

	public WebDriver getBrowserDriver(String browserName, String appUrl) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.BROWSER_LOG_FOLDER+"\\Firefox"+getDateTime()+".log");
			driver = new FirefoxDriver();
			//add extension 
//			FirefoxProfile profile= new FirefoxProfile();
//			File translate = new File(GlobalConstants.BROWSER_EXTENSIONS_FOLDER+"\\to_google_translate-4.0.6-fx.xpi");
//			profile.addExtension(translate);
//			FirefoxOptions options = new FirefoxOptions();
//			options.setProfile(profile);
//			driver = new FirefoxDriver(options);
			
			
		} else if (browserName.equalsIgnoreCase("firefox_headless")) {
			FirefoxOptions options= new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
			
		} else if (browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			//add extendsion for chrome
//			File file = new File(GlobalConstants.BROWSER_EXTENSIONS_FOLDER+"\\extension_2_0_9_0.crx");
//			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(file);
//			driver = new ChromeDriver(options);
			
		} else if (browserName.equalsIgnoreCase("chrome_headless")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver =new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();

		} else {
			System.out.print("Browser not found");
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}
	
	protected int randomNumber() {
		Random number = new Random();
		return number.nextInt(9999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);

		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	protected String getCurrentDay() {
		//DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		//cast to String
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		//cast to String
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getWordPressToday() {
		return getCurrentDay()+"/"+ getCurrentMonth() +"/"+ getCurrentYear() ;
	}
	protected String getToda() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}
	
	protected void showBrowserConsoleLog(WebDriver driver) {
		if(driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging:logList) {
				System.out.println("---------------------"+logging.getLevel().toString()+"---------------------------\n"+logging.getMessage());
			}
			
		}
	}
	
	protected String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date date = new Date();
		return format.format(date).replace(":", "-").replace(" ", "-");
	}
public WebDriver getDriver() {
		return driver;
	}

}
