package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.AbstractPageUI;
import pageUI.wordpress.admin.DashBoardPageUI;
import pageUI.wordpress.admin.PostDetailPageUI;
import pageUI.wordpress.admin.PostPageUI;

public class PostDetailsPageObject extends AbstractPage {
	WebDriver driver;

	public PostDetailsPageObject(WebDriver mapDriver) {
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

	public boolean isCategoryNameDisplayed(String postCategoryCheckBox) {
		waitForElementVisible(driver, PostDetailPageUI.CATEGORY_NAME, postCategoryCheckBox);
		return isElementDisplayed(driver, PostDetailPageUI.CATEGORY_NAME, postCategoryCheckBox);
	}
	public boolean isCategoryNameUndisplayed(String postCategoryCheckBox) {
		
		return isElementUndisplayed(driver, PostDetailPageUI.CATEGORY_NAME, postCategoryCheckBox);
	}

	public boolean isTitleDisplayed(String newPostTitle) {
		waitForElementVisible(driver, PostDetailPageUI.TITLE_NAME, newPostTitle);
		return isElementDisplayed(driver, PostDetailPageUI.TITLE_NAME, newPostTitle);
	}
	public boolean isTitleUndisplayed(String newPostTitle) {
	
		return isElementUndisplayed(driver, PostDetailPageUI.TITLE_NAME, newPostTitle);
	}

	public boolean isImageDisplayed(String featureImage) {
waitForJStoLoad(driver);
		featureImage = featureImage.split("\\.")[0];
		waitForElementVisible(driver, PostDetailPageUI.IMAGE_NAME, featureImage);
		return isElementDisplayed(driver, PostDetailPageUI.IMAGE_NAME, featureImage)
				&&isImageLoaded(driver, PostDetailPageUI.IMAGE_NAME, featureImage);
	}

	public boolean isContentValueDisplayed(String newPostContent) {
		waitForElementVisible(driver, PostDetailPageUI.POST_CONTENT_VALUE, newPostContent);
		return isElementDisplayed(driver, PostDetailPageUI.POST_CONTENT_VALUE, newPostContent);
	}

	public boolean isDateCreatedDisplayed(String createdDay) {
		waitForElementVisible(driver, PostDetailPageUI.CREATED_DATE, createdDay);
		return isElementDisplayed(driver, PostDetailPageUI.CREATED_DATE, createdDay);
	}

	public boolean isAuthorDisplayed(String authorName) {
		waitForElementVisible(driver, PostDetailPageUI.AUTHOR, authorName);
		return isElementDisplayed(driver, PostDetailPageUI.AUTHOR, authorName);
	}
	public boolean isTagDisplayed(String tagValue) {
		waitForElementVisible(driver, PostDetailPageUI.TAG_VALUE, tagValue);
		return isElementDisplayed(driver, PostDetailPageUI.TAG_VALUE, tagValue);
	}
	public boolean isTagUndisplayed(String tagValue) {
		
		return isElementUndisplayed(driver, PostDetailPageUI.TAG_VALUE, tagValue);
	}


	

	
}
