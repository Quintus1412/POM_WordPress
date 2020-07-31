package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.LogInPageUI;

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

	public void clickToContinueOrLoginButton() {
		waitForElementVisible(driver, LogInPageUI.CONTINUE_OR_LOGIN_BUTTON);
		clickToElement(driver, LogInPageUI.CONTINUE_OR_LOGIN_BUTTON);

	}

	public String getEmailOrPassErrorMessage() {
		waitForElementVisible(driver, LogInPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
		return getElementtext(driver, LogInPageUI.EMAIL_OR_PASSWORD_ERROR_MESSAGE);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, LogInPageUI.PASSWORD_TEXTBOX);
		senkeyToElement(driver, LogInPageUI.PASSWORD_TEXTBOX, password);

	}

}
