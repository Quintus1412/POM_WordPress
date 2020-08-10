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

import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager_WordPress;
import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.LogInPageObject;

public class Login_05_Multi_Browser_parallel extends AbstractTest {

	WebDriver driver;
	LogInPageObject loginPage;
	DashBoardPageObject dashboardPage;
	String loginPageUrl;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager_WordPress.getLoginPage(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
		
	}

	 @BeforeMethod
	 public void beforeMethod() {
		 loginPage.openLoginPage(loginPageUrl);
	
	 }

	@Test
	public void TC_01_EmptyEmail() {
		loginPage.inputToEmailTextbox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "Please enter a username or email address.");

	}

	@Test
	public void TC_02_InvalidEmail() {

		loginPage.inputToEmailTextbox("123@123.123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "Please log in using your WordPress.com username instead of your email address.");

	}

	@Test
	public void TC_03_EmailNotExisted() {
		loginPage.inputToEmailTextbox("automation" + randomNumber() + "@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "User does not exist. Would you like to create a new account?");

	}

	@Test
	public void TC_04_EmptyPass() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "Don't forget to enter your password.");
	}

	@Test
	public void TC_05_PassLessThan6Characters() {
		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_06_IncorrectPass() {

		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("123456");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPassErrorMessage(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_07_ValidPass() {

		loginPage.inputToEmailTextbox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPasswordTextBox("automationfc");
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
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
