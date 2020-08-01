package com.bankGuru.login;

import org.testng.annotations.Test;


import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.LogInPageObject;
import pageOjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_RegisterAndLogin {

	WebDriver driver;
	LogInPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	String userIDValue;
	String passwordValue;
	String loginPageURL;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Open LogIn page. Init Login page
		driver.get("http://demo.guru99.com/V4/");
		loginPage = new LogInPageObject(driver);
		loginPageURL = loginPage.getLoginPageURL();
	}

	@Test
	public void TC_01_Register() {
		// Click here link -> Open Register page
		loginPage.clickToHereLink();
		// Event navigate to Register Page
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextbox("quintus"+randomNumber()+"@gmail.com");
		registerPage.clickToSubmitButton();
		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();

		// From Register page-> open Login page
		registerPage.openLoginPage(loginPageURL);

		// Init Login page
		loginPage = new LogInPageObject(driver);

	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		loginPage.clickToLoginButton();
		// Init Home Page
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		
	
	}

	@Test
	public void f() {
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
