package com.wordpress.posts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager_WordPress;
import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.LogInPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailsPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;

public class Admin_01_Create_View_Edit_Delete extends AbstractTest {
	WebDriver driver;
	int fakeNumber = randomNumber();
	String newPostTitle="[SESSION] New Post Title "+fakeNumber;
	String newPostContent="Content of Session "+fakeNumber;
	String newPostTag="new_post_"+ fakeNumber;
	String newPostCheckbox ="NEW LIVE CODING";
	
	@Parameters({ "browser","url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-condition - Open browser");
		driver= getBrowserDriver(browserName, url);
	
		log.info("Pre-condition - STEP 1: Open Login Page");
		loginAdminPage = PageGeneratorManager_WordPress.getLoginAdminPage(driver);
		
		log.info("Pre-condition - STEP 2: Input Email textbox");
		loginAdminPage.inputToEmailTextbox(GlobalConstants.USER_NAME);
		
		log.info("Pre-condition - STEP 3: Click to Continue button");
		loginAdminPage.clickToContinueOrLoginButton();
		
		log.info("Pre-condition - STEP 4: Input Password textbox");
		loginAdminPage.inputToPasswordTextBox(GlobalConstants.PASSWORD);
		
		log.info("Pre-condition - STEP 5: Click to Continue button");
		dashboardAdminPage = loginAdminPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 6: Verify header text displayed");
		Assert.assertTrue(dashboardAdminPage.isHeaderTextDisplayed());
		
	}

	@Test
	public void Admin_Post_01_Create_New_Post() {
	//New Post
		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		newEditPostAdminPage = postsAdminPage.clickToAddNewButton();
		newEditPostAdminPage.inputToTitlePostTextbox(newPostTitle);
		newEditPostAdminPage.inputToPostContentTextbox(newPostContent);
		newEditPostAdminPage.selectCategoryCheckbox(newPostCheckbox);
		newEditPostAdminPage.inputToTagTextbox(newPostTag);
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToSetFeatureImage();
		newEditPostAdminPage.clickToUploadFilesTab();
		newEditPostAdminPage.uploadMultipleFiles(driver, feartureImage);
		verifyTrue(newEditPostAdminPage.areFilesUploadedDisplayed(driver, feartureImage));
		newEditPostAdminPage.clickToSetFeatureImageButton();
		verifyTrue(newEditPostAdminPage.isFeatureImageDisplayed(feartureImage));
		
		newEditPostAdminPage.clickToPublishButton();
		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver,"Post published. "));
		
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox(newPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title",newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author",authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories",newPostCheckbox));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags",newPostTag));
	
		//Navigate to User site
		homeUserPage= postsAdminPage.openEndUserPage(driver);
		
		//Design in Abstract to re-use at search page
		verifyTrue(homeUserPage.isPostDisplayedOnLastedPost(driver,"title","created date"));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,"title","selenium.png"));
		
		
		//Go_To_Post_At_User_Page
		postDetailUserPage = homeUserPage.clickToPostDetailsWithTileName("title");
		verifyTrue(postDetailUserPage.isCategoryNameDisplayed("categoty"));
		verifyTrue(postDetailUserPage.isTitleDisplayed("title"));
		verifyTrue(postDetailUserPage.isImageDisplayed("selenium.png"));
		verifyTrue(postDetailUserPage.isContentDisplayed("Content"));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed("Content"));
		verifyTrue(postDetailUserPage.isAuthorDisplayed("author"));
		
		
		//Search_Post_At_User_Page
		searchResultUserPage= postDetailUserPage.inputToSearchTextboxEndUserPage(driver, "title");
		verifyTrue(searchResultUserPage.isNewPostDisplayedOnLastedPost("category", "title","created date"));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName("title","selenium.png"));
		
	}
	
	
	public void Post_02_Edit_Post_At_Admin_Page() {
	//Navigate to Admin page
		dashboardAdminPage = searchResultUserPage.openLogedinAdminPage(driver);
		
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox("");
		postsAdminPage.clickToSearchButton();
		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("title","author","category","tag"));
		
		//click to post detail
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitle("");
		
		
		// Edit post
		newEditPostAdminPage.inputToTitlePostTextbox("");
		newEditPostAdminPage.inputToPostContentTextbox("Edit_content");
		newEditPostAdminPage.deselectCategoryCheckbox("NEW LIVE CODING");
		newEditPostAdminPage.selectCategoryCheckbox("EDIT LIVE CODING");
		newEditPostAdminPage.inputToTagTextbox("tag_edit_name");
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToDeleteTagIconWithTagName("tag_new_name");
	
		newEditPostAdminPage.clickToUpdateButton();
		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue("Post Updated"));
		
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox("");
		postsAdminPage.clickToSearchButton();
		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("edit_title","author","edit_category","tag_edit_name"));
		
		//Navigate to User site
		homeUserPage= postsAdminPage.openEndUserPage(driver);
		
		//Design in Abstract to re-use at search page
		verifyTrue(homeUserPage.isNewPostDisplayedOnLastedPost("edit_title", "edit_category","created date"));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName("title","selenium.png"));
		
		
		//Go_To_Post_At_User_Page
		postDetailUserPage = homeUserPage.clickToPostDetailsWithTileName("edit_title");
		verifyTrue(postDetailUserPage.isCategoryNameDisplayed("edit_category"));
		verifyTrue(postDetailUserPage.isTitleDisplayed("edit_title"));
		verifyTrue(postDetailUserPage.isImageDisplayed("selenium.png"));
		verifyTrue(postDetailUserPage.isContentDisplayed("Edit_content"));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed("date_create"));
		verifyTrue(postDetailUserPage.isAuthorDisplayed("author"));
		
		
		//Search_Post_At_User_Page
		searchResultUserPage= postDetailUserPage.inputToSearchTextboxEndUserPage(driver, "edit_title");
		verifyTrue(searchResultUserPage.isNewPostDisplayedOnLastedPost("edit_category", "edit_title","created date"));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName("edit_title","selenium.png"));
	}

	
	
	public void Post_03_Delete_Post_At_User_Page() {
		dashboardAdminPage = searchResultUserPage.openLogedinAdminPage(driver);
		
		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox("edit_title");
		postsAdminPage.clickToSearchButton();
		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("edit_title","author","edit_category","tag_edit_name"));
		
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitle("edit_title");
		postsAdminPage = newEditPostAdminPage.clicktoMoveToTrashButon();
		verifyTrue(postsAdminPage.isSuccessMessageDisplayedWithValue("Deletesuccess"));
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox("edit_title");
		postsAdminPage.clickToSearchButton();
		
		//delete post is undisplayed
		verifyFalse(postsAdminPage.isOnlyOneRowDisplayed("edit_title","author","edit_category","tag_edit_name"));
		verifyTrue(postsAdminPage.isNoPostFoundDisplayed("No post found"));
		
		//View_Post_At_User_Page
		homeUserPage= postsAdminPage.openEndUserPage(driver);
		
		//Design in Abstract to re-use at search page
		verifyFalse(homeUserPage.isNewPostDisplayedOnLastedPost("edit_title", "edit_category","created date"));
		verifyFalse(homeUserPage.isPostImageDisplayedAtPostTitleName("title","selenium.png"));
		
		//Search_Post_At_User_Page
		searchResultUserPage= homeUserPage.inputToSearchTextboxEndUserPage(driver, "edit_title");
		verifyTrue(searchResultUserPage.isNewPostDisplayedOnLastedPost("edit_category", "edit_title","created date"));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName("edit_title","selenium.png"));
	}
		
	

	@AfterClass
	public void afterClass() {
		log.info("Post-condition - Close browser");
		//closeBrowserAndDriver(driver);
	}
	
	String feartureImage = "Selenium.jpg";
	String authorName= "Automation FC";
	
	LogInPageObject loginAdminPage;
	DashBoardPageObject dashboardAdminPage;
	PostsPageObject postsAdminPage;
	MediaPageObject mediaAdminPage;
	PagesPageObject pagesAdminPage;
	NewEditPostsPageObject newEditPostAdminPage;
	HomePageObject homeUserPage;
	PostDetailsPageObject postDetailUserPage;
	SearchResultPageObject searchResultUserPage;

}
