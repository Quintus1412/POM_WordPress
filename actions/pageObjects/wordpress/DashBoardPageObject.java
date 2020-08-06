package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.AbstractPageUI;
import pageUI.wordpress.DashBoardPageUI;
import pageUI.wordpress.PostPageUI;

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

	public void clickToScreenOption() {
		waitForElementVisible(driver, DashBoardPageUI.SCREEN_OPTION_BUTTON);
		clickToElement(driver, DashBoardPageUI.SCREEN_OPTION_BUTTON);
	}

	public boolean isActivityCheckboxDisplayed() {
		
		return isElementDisplayed(driver, DashBoardPageUI.ACTIVITY_CHECKBOX_HIDE);
	}

	public boolean isAllPostsSubMenuIsDisplayed() {
		
		return isElementDisplayed(driver, PostPageUI.ALL_POST_SUB_MENU);
	}

	public boolean isPlansMenuDisplayed() {
		
		return isElementDisplayed(driver,AbstractPageUI.PLANS_LINK);
	}
	public boolean isPlansMenutUndisplayed() {
		
		return isElementUndisplayed(driver,AbstractPageUI.PLANS_LINK);
	}

	

	
}
