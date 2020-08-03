package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.DashBoardPageObject;
import pageObjects.wordpress.LogInPageObject;
import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;



public class PageGeneratorManager_WordPress {
	public static LogInPageObject getLoginPage(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static PagesPageObject getPagesPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	public static MediaPageObject getMediaPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}
	public static PostsPageObject getPostsPage(WebDriver driver) {
		return new PostsPageObject(driver);
	}
	public static DashBoardPageObject getDashBoardPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	
}
