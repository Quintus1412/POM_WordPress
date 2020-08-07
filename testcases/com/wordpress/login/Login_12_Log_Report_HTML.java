package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.AbstractTest;
import commons.PageGeneratorManager_WordPress;
import pageObjects.wordpress.DashBoardPageObject;
import pageObjects.wordpress.LogInPageObject;
import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;

public class Login_12_Log_Report_HTML extends AbstractTest {

	WebDriver driver;
	// driver is undefined = xxxxx-xxx-xx-xxx-xxxx
	LogInPageObject loginPage;
	DashBoardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

	DriverManager driverManager;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getDriverManager(browserName);
		/// driver = 1234-56245-145-6524
		
		log.info("Pre-condition - Open page");
		driver = driverManager.getDriver("https://automationfc.wordpress.com/wp-admin/");
		
		log.info("Pre-condition - STEP 1: Open Login Page");
		loginPage = PageGeneratorManager_WordPress.getLoginPage(driver);
	}

	@Test
	public void TC_01_LoginToSytem() {
		log.info("Pre-condition - STEP 2: Input Email textbox");
		loginPage.inputToEmailTextbox("automationeditor");
		
		log.info("Pre-condition - STEP 3: Click to Continue button");
		loginPage.clickToContinueOrLoginButton();
		
		log.info("Pre-condition - STEP 4: Input Password textbox");
		loginPage.inputToPasswordTextBox("automationfc");
		
		log.info("Pre-condition - STEP 5: Click to Continue button");
		dashboardPage = loginPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 6: Verify header text displayed");
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test
	public void TC_02_ActivityCheckBoxIsUndisplayed() {

		log.info("ActivityCheckBoxIsUndisplayed - STEP 1: Click to open Screen Option");
		dashboardPage.clickToScreenOption();
		dashboardPage.sleepInSecond(2);
		
		log.info("ActivityCheckBoxIsUndisplayed - STEP 2: Verify Screen Option displayed");
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed());
		// close
		log.info("ActivityCheckBoxIsUndisplayed - STEP 3: Click to close Screen Option");
		dashboardPage.clickToScreenOption();
		dashboardPage.sleepInSecond(2);
		
		log.info("ActivityCheckBoxIsUndisplayed - STEP 4: Verify Activity checkbox is undisplayed");
		verifyFalse(dashboardPage.isActivityCheckboxDisplayed());
		
		log.info("ActivityCheckBoxIsUndisplayed - STEP 5: Verify Posts menu is undisplayed");
		verifyFalse(dashboardPage.isAllPostsSubMenuIsDisplayed());
	}
	

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - Close Browser");
		driver.quit();
	}

}
