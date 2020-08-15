package com.wordpress.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class Login_08_Dynamic_Locator_Rest_Param extends AbstractTest {

	WebDriver driver;
	//driver is undefined = xxxxx-xxx-xx-xxx-xxxx
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
		//driver = driverManager.getDriver();
		// Open URL -> navigate to login page
		//loginPage = PageGeneratorManager_WordPress.getLoginPage(driver);
	}

	@Test
	public void TC_01_LoginToSytem() {
		
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("automationfc");
		
		// valid user/pass -> navigate to Dashboard
		dashboardPage=	loginPage.clickToContinueOrLoginButton();
		
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}
	@Test
	public void TC_02_NavigateToFewPage() {
		//Navigate from Dashboard to Post
		postsPage = (PostsPageObject) dashboardPage.clickToDyamicAFewPageMenu(driver, "post");
		
		//Navigate from Post to Pages
		pagesPage =  (PagesPageObject) postsPage.clickToDyamicAFewPageMenu(driver, "page");
		
		//Navigate from Pages to Media
		mediaPage =(MediaPageObject) pagesPage.clickToDyamicAFewPageMenu(driver, "media");
		
		//Navigate from Media to Post
		postsPage =  (PostsPageObject) mediaPage.clickToDyamicAFewPageMenu(driver, "post");
		//Navigate from Post to media
		
		mediaPage = (MediaPageObject) postsPage.clickToDyamicAFewPageMenu(driver, "media");
		
		dashboardPage = (DashBoardPageObject) mediaPage.clickToDyamicAFewPageMenu(driver, "dashboard");
		
		
	}
	@Test
	public void TC_03_NavigateToALotsPage() {
		
		
		dashboardPage.openMenuPageByName(driver, "post");
		postsPage = PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		//Navigate from Post to Pages
		postsPage.openMenuPageByName(driver, "page");
		pagesPage =  PageGeneratorManager_WordPress.getPagesPage(driver);
		
		//Navigate from Pages to Media
		pagesPage.openMenuPageByName(driver, "media");
		mediaPage =PageGeneratorManager_WordPress.getMediaAdminPage(driver);
		
		mediaPage.openMenuPageByName(driver, "dashboard");
		dashboardPage = PageGeneratorManager_WordPress.getDashBoardAdminPage(driver);
		
	}

	public int randomNumber() {
		Random number = new Random(9999);
		return number.nextInt();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
