package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.MediaPageUI;
import pageUI.wordpress.admin.PagesPageUI;

public class MediaPageObject extends AbstractPage {

	WebDriver driver;
	public MediaPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Media page is"+ driver.toString());
	}
	public void clickToAddNewButton() {
		waitForElementVisible(driver, MediaPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, MediaPageUI.ADD_NEW_BUTTON);
		
	}
	
	
	
}
