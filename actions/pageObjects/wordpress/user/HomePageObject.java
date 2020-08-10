package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_WordPress;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageUI.wordpress.admin.AbstractPageUI;
import pageUI.wordpress.admin.DashBoardPageUI;
import pageUI.wordpress.admin.PostPageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;

	public HomePageObject(WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Dashboard page is"+ driver.toString());
	}

	

	public PostDetailsPageObject clickToPostDetailsWithTileName(String string) {
		
		return PageGeneratorManager_WordPress.getPostDetailUserPage(driver);
	}



	



	

	
}
