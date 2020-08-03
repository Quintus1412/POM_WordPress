package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.DashBoardPageUI;

public class DashBoardPageObject extends AbstractPage {
	WebDriver driver;

	public DashBoardPageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Dashboard page is"+ driver.toString());
	}

	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashBoardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashBoardPageUI.HEADER_TEXT);

	}

	
}
