package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGuru.HomePageUI;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	
	public HomePageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at home page"+ driver.toString());
	}

	public boolean isWelcomeMessageDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
		return isElementDisplayed(driver, HomePageUI.WELCOME_MESSAGE_TEXT);
	}
}
