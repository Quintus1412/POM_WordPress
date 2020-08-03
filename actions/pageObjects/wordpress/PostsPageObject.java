package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageUI.wordpress.PagesPageUI;
import pageUI.wordpress.PostPageUI;

public class PostsPageObject extends AbstractPage {
	WebDriver driver;
	public PostsPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Post page is"+ driver.toString());
	}
	
	
	
}
