package Browsers_Factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commons.GlobalConstants;



public abstract class DriverManager {
	protected WebDriver driver;
	
	protected abstract void createDiver();
	
	public void quitDriver() {
		if(driver!= null) {
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriver getDriver(String pageURL) {
		if(driver==null) {
			createDiver();
		}
		
	driver.get(pageURL);
		//driver.get(GlobalConstants.DATATABLE_JQUERY_URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
		/// driver = 1234-56245-145-6524
	}

}
