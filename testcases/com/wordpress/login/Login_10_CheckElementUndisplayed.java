package com.wordpress.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager_WordPress;
import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.LogInPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;

public class Login_10_CheckElementUndisplayed extends AbstractTest {

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
		driver = driverManager.getDriver("https://automationfc.wordpress.com/wp-admin/");
		// Open URL -> navigate to login page
		loginPage = PageGeneratorManager_WordPress.getLoginPage(driver);
	}

	@Test
	public void TC_01_LoginToSytem() {

		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("automationfc");

		// valid user/pass -> navigate to Dashboard
		dashboardPage = loginPage.clickToContinueOrLoginButton();

		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@Test
	public void TC_02_Element_Undispplayed_IN_DOM() {

		// open
		dashboardPage.clickToScreenOption();
		dashboardPage.sleepInSecond(2);
		Assert.assertTrue(dashboardPage.isActivityCheckboxDisplayed());
		// close
		dashboardPage.clickToScreenOption();
		dashboardPage.sleepInSecond(2);
		Assert.assertFalse(dashboardPage.isActivityCheckboxDisplayed());

		Assert.assertFalse(dashboardPage.isAllPostsSubMenuIsDisplayed());
	}
	
	@Test
	public void TC_03_Element_Undispplayed_OUT_DOM() {
		//find element has to wait until timeout
		Assert.assertTrue(dashboardPage.isPlansMenutUndisplayed());	
		Assert.assertFalse(dashboardPage.isAllPostsSubMenuIsDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
