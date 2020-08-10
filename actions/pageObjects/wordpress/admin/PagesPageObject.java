package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.admin.DashBoardPageUI;
import pageUI.wordpress.admin.PagesPageUI;

public class PagesPageObject extends AbstractPage {

	WebDriver driver;
	public PagesPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		
	}
	
	
}
