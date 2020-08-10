package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.GlobalConstants;
import commons.PageGeneratorManager_WordPress;
import pageUI.jquery.DataTableJQueryUI;
import pageUI.wordpress.admin.PagesPageUI;
import pageUI.wordpress.admin.PostPageUI;

public class PostsPageObject extends AbstractPage {
	WebDriver driver;
	public PostsPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.println("Driver at Post page is"+ driver.toString());
	}
	public NewEditPostsPageObject clickToPostDetailByTitle() {
		// TODO Auto-generated method stub
		return PageGeneratorManager_WordPress.getNewEditPostsAdminPage(driver);
	}
	public NewEditPostsPageObject clickToAddNewButton() {
		waitForElementClickable(driver, PostPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager_WordPress.getNewEditPostsAdminPage(driver);
	}
	public void inputToSearchTextbox(String postTitle) {
		waitForElementVisible(driver, PostPageUI.SEARCH_POST_TEXTBOX);
		senkeyToElement(driver, PostPageUI.SEARCH_POST_TEXTBOX, postTitle);
		
	}
	public void clickToSearchButton() {
		waitForElementClickable(driver, PostPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, PostPageUI.SEARCH_POST_BUTTON);
		
		
	}

	

	
	
	
}
