package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_BankGuRu;
import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
WebDriver driver;
	
	public RegisterPageObject (WebDriver mapDriver)
	{
		driver = mapDriver;
		System.out.print("Driver at register page"+ driver.toString());
	}
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		senkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
		
		
	}

	public void clickToSubmitButton() {
		waitForElementVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
		
	}

	public String getUserIDText() {
		waitForElementVisible(driver, RegisterPageUI.USERID_TEXT);
		return getElementtext(driver, RegisterPageUI.USERID_TEXT);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, RegisterPageUI.USERPASS_TEXT);
		return getElementtext(driver, RegisterPageUI.USERPASS_TEXT);
	}

	public LogInPageObject openLoginPage(String loginPageURL) {
		openURL(driver, loginPageURL);
		return PageGeneratorManager_BankGuRu.getLoginPage(driver);

		
		
	}

}

