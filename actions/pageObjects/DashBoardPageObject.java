package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.DashBoardPageUI;

public class DashBoardPageObject extends AbstractPage {
	WebDriver driver;

	public DashBoardPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashBoardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashBoardPageUI.HEADER_TEXT);

	}

}
