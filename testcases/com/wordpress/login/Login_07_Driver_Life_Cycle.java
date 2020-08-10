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

public class Login_07_Driver_Life_Cycle extends AbstractTest {

	WebDriver driver;
	//driver is undefined = xxxxx-xxx-xx-xxx-xxxx
	LogInPageObject loginPage;
	DashBoardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	
	
	String loginPageUrl;
	DriverManager driverManager;

	@Parameters({ "browser","url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		
		//driverManager = BrowserDriverFactory.getDriverManager(browserName);
		
		loginPage = PageGeneratorManager_WordPress.getLoginPage(driver);
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
	public void TC_02_NavigateToPage() {
		//Navigate from Dashboard to Post
		postsPage = dashboardPage.clickToPostsMenu(driver);
		
		//Navigate from Post to Pages
		pagesPage = postsPage.clickToPagesMenu(driver);
		
		//Navigate from Pages to Media
		mediaPage= pagesPage.clickToMediaMenu(driver);
		
		//Navigate from Media to Post
		postsPage= mediaPage.clickToPostsMenu(driver);
		
		//Navigate from Post to media
		
		mediaPage = postsPage.clickToMediaMenu(driver);
		
		dashboardPage = mediaPage.clickToDashboardMenu(driver);
	
		
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
