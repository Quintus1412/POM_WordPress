package Browsers_Factory;




import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Chrome_Driver_Manager extends DriverManager {

	@Override
	protected void createDiver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		capabilities.setCapability (ChromeOptions.CAPABILITY, options);	
	}
}
