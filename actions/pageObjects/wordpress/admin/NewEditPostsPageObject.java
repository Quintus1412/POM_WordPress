package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.NewEditPostPageUI;

public class NewEditPostsPageObject extends AbstractPage  {
	WebDriver driver;
	public NewEditPostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public static final String ADD_NEW_BUTTON ="//div[@id='wp-media-grid']/a[text()='Add New']";
	public static final String UPLOADING_PROGRESS_IMAGE="//div[@class='thumbnail']//div[@class='media-progress-bar']";
	public void inputToPostContentTextbox(String value) {
		switchToFrameOrIframe(driver, NewEditPostPageUI.TINY_MCE_IFRAME);
		senkeyToElement(driver, NewEditPostPageUI.TINY_MCE_TEXTBOX, value);
		switchToDefaultContent(driver);
		
	}
	public void clickToPublishButton() {
		scrollToElement(driver, NewEditPostPageUI.PUBLISH_BUTTON);
		waitForElementVisible(driver, NewEditPostPageUI.PUBLISH_BUTTON);
		clickToElementByJS(driver, NewEditPostPageUI.PUBLISH_BUTTON);
		
		
		
		
	}
	public void clickToUpdateButton() {
		//wait for 
		
	}
	public PostsPageObject clicktoMoveToTrashButon() {
		// TODO Auto-generated method stub
		return PageGeneratorManager_WordPress.getPostsAdminPage(driver);
	}
	
	public void inputToTitlePostTextbox( String title) {
		waitForElementVisible(driver, NewEditPostPageUI.ADD_TITLE_TEXTBOX);
		senkeyToElement(driver, NewEditPostPageUI.ADD_TITLE_TEXTBOX, title);
		
	}
	public void selectCategoryCheckbox(String checkboxLableText) {
		scrollToElement(driver, NewEditPostPageUI.CATEGORY_BOX);
		sleepInSecond(2);
		
		waitForElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX,checkboxLableText);
		scrollToElement(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLableText);
		clickToElementByJS(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLableText);
		//clickToElement(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLableText);
		
	}
	public void inputToTagTextbox(String tagValue) {
		waitForElementVisible(driver, NewEditPostPageUI.TAG_TEXTBOX);
		senkeyToElement(driver, NewEditPostPageUI.TAG_TEXTBOX, tagValue);
		
	}
	public void clickToAddTagButton() {
		waitForElementClickable(driver, NewEditPostPageUI.TAG_BUTTON);
		clickToElement(driver, NewEditPostPageUI.TAG_BUTTON);
		
	}
	public void clickToSetFeatureImage() {
		waitForElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_LINK);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_LINK);	
	}
	public void clickToUploadFilesTab() {
		waitForElementClickable(driver, NewEditPostPageUI.UPLOAD_FILE_BUTTON_TAB);
		clickToElement(driver, NewEditPostPageUI.UPLOAD_FILE_BUTTON_TAB);
		
	}
	public void clickToSetFeatureImageButton() {
		waitForElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_BUTTON);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMAGE_BUTTON);	
	}
	public boolean isFeatureImageDisplayed(String imageName) {
		String[] file= imageName.split("\\.");
		
	waitForElementVisible(driver, NewEditPostPageUI.FEATURE_IMAGE_THUMBNAIL, file[0].toLowerCase());	
	return isElementDisplayed(driver,NewEditPostPageUI.FEATURE_IMAGE_THUMBNAIL, file[0].toLowerCase());
	}
	
	
	
}
