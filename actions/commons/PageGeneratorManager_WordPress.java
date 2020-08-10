package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.LogInPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailsPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;



public class PageGeneratorManager_WordPress {
	public static LogInPageObject getLoginAdminPage(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static PagesPageObject getPagesAdminPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	public static MediaPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}
	public static PostsPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsPageObject(driver);
	}
	public static DashBoardPageObject getDashBoardAdminPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	public static NewEditPostsPageObject getNewEditPostsAdminPage(WebDriver driver) {
		return new NewEditPostsPageObject(driver);
	}
	public static HomePageObject getHomeUserPage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static PostDetailsPageObject getPostDetailUserPage(WebDriver driver) {
		return new PostDetailsPageObject(driver);
	}
	public static SearchResultPageObject getSearchResultUserPage(WebDriver driver) {
		return new SearchResultPageObject(driver);
	}
	
}
