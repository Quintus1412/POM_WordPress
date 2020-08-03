package com.bankGuru.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.PageGeneratorManager_BankGuRu;
import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.LogInPageObject;
import pageOjects.bankGuru.RegisterPageObject;

public class Login_01_RegisterAndLogin {

	WebDriver driver;
	LogInPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	DriverManager driverManager;

	String userIDValue;
	String passwordValue;
	String loginPageURL;
	
	@Parameters({ "browser" })
	
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getDriverManager(browserName);
		driver = driverManager.getDriver();
		// Open LogIn page. Init Login page
		//driver.get("http://demo.guru99.com/V4/");
		//loginPage = new LogInPageObject(driver);
		
		loginPage = PageGeneratorManager_BankGuRu.getLoginPage(driver);
		loginPageURL = loginPage.getLoginPageURL();
		
	}

	@Test
	public void TC_01_Register() {
		// Click here link -> Open Register page
		registerPage = loginPage.clickToHereLink();	
		
		registerPage.inputToEmailTextbox("quintus"+randomNumber()+"@gmail.com");
		registerPage.clickToSubmitButton();
		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();

		// From Register page-> open Login page
		loginPage = registerPage.openLoginPage(loginPageURL);
		
		

	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	
	}

	@Test
	public void TC_03_LogOut() {
		homePage.logOutHomePage();
		
		//Assert.assertEquals("You Have Succesfully Logged Out!!", (driver));
		//Assert.assertEquals(driver.getCurrentUrl(), "http://demo.guru99.com/V4/");
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
