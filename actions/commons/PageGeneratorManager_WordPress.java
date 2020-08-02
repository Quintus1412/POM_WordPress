package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.DashBoardPageObject;
import pageObjects.wordpress.LogInPageObject;



public class PageGeneratorManager_WordPress {
	public static LogInPageObject getLoginPage(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static DashBoardPageObject getRegisterPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	
}
