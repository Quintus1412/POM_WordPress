package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	 WebDriver driver;
	 
	public WebDriver getBrowserDriver (String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			//WebDriverManager.firefoxdriver().version("70.0.0").setup();
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("chrome_headless")) {
			
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().arch64().setup();
			driver = new EdgeDriver();
		
		} else {
			System.out.print("Browser not found");
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.wordpress.com/wp-admin/");
		return driver;
	}
	
	
}
