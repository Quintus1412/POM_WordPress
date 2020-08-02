package commons;

import org.openqa.selenium.WebDriver;

import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.LogInPageObject;
import pageOjects.bankGuru.RegisterPageObject;

public class PageGeneratorManager_BankGuRu {
	public static LogInPageObject getLoginPage(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
}
