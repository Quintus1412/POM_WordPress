package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_BankGuRu;
import pageUI.bankGuru.LogInPageUI;

public class LogInPageObject extends AbstractPage {
	WebDriver driver;

	public LogInPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at login page" + driver.toString());
	}

	public String getLoginPageURL() {
		return getCurrentUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementClickable(driver, LogInPageUI.HERE_LINK);
		clickToElement(driver, LogInPageUI.HERE_LINK);
		return PageGeneratorManager_BankGuRu.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, LogInPageUI.USER_ID_TEXTBOX);
		senkeyToElement(driver, LogInPageUI.USER_ID_TEXTBOX, userIDValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LogInPageUI.USER_PASSWORD_TEXTBOX);
		senkeyToElement(driver, LogInPageUI.USER_PASSWORD_TEXTBOX, passwordValue);

	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LogInPageUI.LOGIN_BUTTON);
		clickToElement(driver, LogInPageUI.LOGIN_BUTTON);
		return PageGeneratorManager_BankGuRu.getHomePage(driver);
	}


}
