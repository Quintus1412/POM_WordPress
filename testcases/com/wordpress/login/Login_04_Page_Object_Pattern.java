package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;
import pageObjects.DashBoardPageObject;
import pageObjects.LogInPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_04_Page_Object_Pattern extends AbstractPage {

	WebDriver driver;
	LogInPageObject loginPage;
	DashBoardPageObject dashboardPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.wordpress.com/wp-admin/");

		loginPage = new LogInPageObject(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		openURL(driver, "https://automationfc.wordpress.com/wp-admin/");
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
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);

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
