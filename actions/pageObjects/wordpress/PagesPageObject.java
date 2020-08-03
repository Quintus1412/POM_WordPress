package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.DashBoardPageUI;
import pageUI.wordpress.PagesPageUI;

public class PagesPageObject extends AbstractPage {

	WebDriver driver;
	public PagesPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Pages page is"+ driver.toString());
	}
	
	
}
