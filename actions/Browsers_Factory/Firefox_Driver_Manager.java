package Browsers_Factory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Firefox_Driver_Manager extends DriverManager {

	@Override
	protected void createDiver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	//	String rootFolder = System.getProperty("user.dir");
//		FirefoxProfile profile = new FirefoxProfile();
//		DesiredCapabilities capability = DesiredCapabilities.firefox():
//		profile.setAcceptUntrusted Certificates(false); 
//		profile.setAssumeUntrusted Certificatelssuer(true); 
//		profile.setPreference("dom.webnotifications.enabled", false);
//		profile.setPreference("browser.download folderList", 2);
//			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
//profile.setPreference("browser.download.manager.showWhenStarting", false);
//	profile.setPreference("browser.download.dir", rootFolder + "\downloadFiles");
//profile.setPreference("browser.download.download Dir", rootFolder + "\\downloadFiles");
//profile.setPreference("browser.download.defaultFolder", rootFolder + "\\downloadFiles");
//		profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/anytexti ,text/plain,text/html,application/plain");
//		capability = DesiredCapabilities.firefox();
//			capability.setCapability(FirefoxDriver.PROFILE, profile);
//				driver = new FirefoxDriver(capability);

		
	}

}
