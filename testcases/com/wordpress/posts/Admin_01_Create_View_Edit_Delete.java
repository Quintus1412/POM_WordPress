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
	
	String newPostTitle="[SESSION] New_Post_Title_"+fakeNumber;
	String newPostContent="Content of Session "+fakeNumber;
	String newPostTag="new_post_"+ fakeNumber;
	String newPostCategory ="NEW LIVE CODING";
	
	String editPostTitle="[SESSION] Edit_Post_Title_"+fakeNumber;
	String editPostTag="edit_tag_"+ fakeNumber;
	String editPostCategory="EDIT LIVE CODING";
	
	
	
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
		newEditPostAdminPage.selectCategoryCheckbox(newPostCategory);
		newEditPostAdminPage.inputToTagTextbox(newPostTag);
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToSetFeatureImage();
		newEditPostAdminPage.clickToUploadFilesTab();
		newEditPostAdminPage.uploadMultipleFiles(driver, featureImage);
		verifyTrue(newEditPostAdminPage.areFilesUploadedDisplayed(driver, featureImage));
		newEditPostAdminPage.clickToSetFeatureImageButton();
		verifyTrue(newEditPostAdminPage.isFeatureImageDisplayed(featureImage));
		
		newEditPostAdminPage.clickToPublishOrUpdateButton();
		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver,"Post published. "));
		
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox(newPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title",newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author",authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories",newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags",newPostTag));
	
		//Navigate to User site
		homeUserPage= postsAdminPage.openEndUserPage(driver);
		
		//Design in Abstract to re-use at search page
		verifyTrue(homeUserPage.isPostDisplayedOnLastedPost(driver,newPostCategory,newPostTitle,getWordPressToday()));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,newPostTitle,featureImage));
		
		
		//Go_To_Post_At_User_Page
		log.info("Go to Post detail");
		postDetailUserPage = homeUserPage.clickToPostDetailsWithTileName(driver, newPostTitle);
		
		log.info("Check the post info is correct");
		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleDisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImage));
		verifyTrue(postDetailUserPage.isTagDisplayed(newPostTag));
		verifyTrue(postDetailUserPage.isContentValueDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(authorName));
		
		
		//Search_Post_At_User_Page
		log.info("Search post title is existed in end user");
		searchResultUserPage= postDetailUserPage.inputToSearchTextboxEndUserPage(driver, newPostTitle);
		verifyTrue(searchResultUserPage.isPostTitleDisplayedOnHeader(newPostTitle));
		verifyTrue(searchResultUserPage.isPostDisplayedOnLastedPost(driver,newPostCategory,newPostTitle,getWordPressToday()));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver,newPostTitle,featureImage));
		
//		
	}
	
	@Test
	public void Post_02_Edit_Post_At_Admin_Page() {
	//Navigate to Admin page
		dashboardAdminPage = searchResultUserPage.openLogedinAdminPage(driver);
		
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox(newPostTitle);
		postsAdminPage.clickToSearchButton();
		
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title",newPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author",authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories",newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags",newPostTag));
		
		//click to post detail
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitle(newPostTitle);
		
		
		// Edit post
		log.info("Edit post in Admin");
		newEditPostAdminPage.inputToTitlePostTextbox(editPostTitle);
	
		newEditPostAdminPage.deselectCategoryCheckbox(newPostCategory);
		newEditPostAdminPage.selectCategoryCheckbox(editPostCategory);
		newEditPostAdminPage.inputToTagTextbox(editPostTag);
		newEditPostAdminPage.clickToAddTagButton();
		newEditPostAdminPage.clickToDeleteTagIconWithTagName(newPostTag);
	
		newEditPostAdminPage.clickToPublishOrUpdateButton();
		log.info("TC2: Post updated");
		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue(driver,"Post updated. "));
		
		
		
		//Search_Post_At_Admin_Page
		log.info("TC2: open POst PAGE");
		newEditPostAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		log.info("TC2: Search post after editing");
		postsAdminPage.inputToSearchTextbox(editPostTitle);
		postsAdminPage.clickToSearchButton();
		log.info("TC2: Verify after editing");
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Title",editPostTitle));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Author",authorName));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories",editPostCategory));
		//verifyFalse(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Categories",newPostCategory));
		verifyTrue(postsAdminPage.isRowValueDisplayedAtColumn(driver, "Tags",editPostTag));
	
		//Navigate to User site
		log.info("TC2: Open end userpage and Search post after editing on end user page");
		homeUserPage= postsAdminPage.openEndUserPage(driver);
		
		//Design in Abstract to re-use at search page
		verifyTrue(homeUserPage.isPostDisplayedOnLastedPost(driver,editPostCategory,editPostTitle,getWordPressToday()));
		verifyTrue(homeUserPage.isPostImageDisplayedAtPostTitleName(driver,editPostTitle,featureImage));
		
		
		//Go_To_Post_At_User_Page
		log.info("TC2: Go to Post detail on end user page");
		postDetailUserPage = homeUserPage.clickToPostDetailsWithTileName(driver, editPostTitle);
		
		log.info("TC2: Check the post info is correct");
		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(editPostCategory));
		verifyTrue(postDetailUserPage.isCategoryNameUndisplayed(newPostCategory));
		verifyTrue(postDetailUserPage.isTitleDisplayed(editPostTitle));
		verifyTrue(postDetailUserPage.isTitleUndisplayed(newPostTitle));
		verifyTrue(postDetailUserPage.isImageDisplayed(featureImage));
		verifyTrue(postDetailUserPage.isContentValueDisplayed(newPostContent));
		verifyTrue(postDetailUserPage.isTagDisplayed(editPostTag));
		verifyTrue(postDetailUserPage.isTagUndisplayed(newPostTag));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(today));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(authorName));
		
		
		//Search_Post_At_User_Page
		log.info("TC2: Search post title after updated is existed in end user");
		searchResultUserPage= postDetailUserPage.inputToSearchTextboxEndUserPage(driver, editPostTitle);
		verifyTrue(searchResultUserPage.isPostTitleDisplayedOnHeader(editPostTitle));
		verifyTrue(searchResultUserPage.isPostDisplayedOnLastedPost(driver,editPostCategory,editPostTitle,getWordPressToday()));
		verifyTrue(searchResultUserPage.isPostImageDisplayedAtPostTitleName(driver,editPostTitle,featureImage));

	}

	
	
	public void Post_03_Delete_Post_At_User_Page() {
		dashboardAdminPage = searchResultUserPage.openLogedinAdminPage(driver);
		
		dashboardAdminPage.openMenuPageByPageName(driver, "Posts");
		postsAdminPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox(editPostTitle);
		postsAdminPage.clickToSearchButton();
		verifyTrue(postsAdminPage.isOnlyOneRowDisplayed("edit_title","author","edit_category","tag_edit_name"));
		
		newEditPostAdminPage = postsAdminPage.clickToPostDetailByTitle("edit_title");
		postsAdminPage = newEditPostAdminPage.clicktoMoveToTrashButon();
		verifyTrue(postsAdminPage.isSuccessMessageDisplayedWithValue("Deletesuccess"));
		
		//Search_Post_At_Admin_Page
		postsAdminPage.inputToSearchTextbox("edit_title");
		postsAdminPage.clickToSearchButton();
		
		//deleted post is undisplayed
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
	
	String featureImage = "selenium.jpg";
	String authorName= "Automation FC";
	String today= getWordPressToday();
	
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
