package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.AbstractPageUI;
import pageUI.wordpress.admin.DashBoardPageUI;
import pageUI.wordpress.admin.PostPageUI;
import pageUI.wordpress.admin.SearchResultPageUI;

public class SearchResultPageObject extends AbstractPage {
	WebDriver driver;

	public SearchResultPageObject(WebDriver mapDriver) {
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

	public boolean isPostTitleDisplayedOnHeader(String newpostTitle) {
		waitForElementVisible(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newpostTitle);
		return isElementDisplayed(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, newpostTitle);
	}
	public boolean isPostTitleUndisplayedOnHeader(String editpostTitle) {
		
		return isElementUndisplayed(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, editpostTitle);
	}

	

	
}
