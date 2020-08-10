package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.LogInPageUI;

public class LogInPageObject extends AbstractPage {
	WebDriver driver;
	LogInPageUI loginUI;

	public LogInPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, LogInPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, LogInPageUI.EMAIL_TEXTBOX, email);

	}

	public DashBoardPageObject clickToContinueOrLoginButton() {
		waitForElementVisible(driver, LogInPageUI.CONTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LogInPageUI.CONTINUE_OR_LOGIN_BUTTON);
		return PageGeneratorManager_WordPress.getDashBoardAdminPage(driver);
		

	}

	public String getEmailOrPassErrorMessage() {
		waitForElementVisible(driver, LogInPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
		return getElementtext(driver, LogInPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, LogInPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LogInPageUI.PASSWORD_TEXTBOX, password);

	}

	public void openLoginPage(String loginPageUrl) {
		openURL(driver, loginPageUrl);
		
	}

	public String getLoginPageUrl() {
		// TODO Auto-generated method stub
		return getCurrentUrl(driver);
	}



	
}
