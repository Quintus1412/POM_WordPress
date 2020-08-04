package com.bankGuru.login;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.PageGeneratorManager_BankGuRu;
import commons.PageGeneratorManager_WordPress;
import pageOjects.bankGuru.BalancePageObject;
import pageOjects.bankGuru.DeleteCustomerPageObject;
import pageOjects.bankGuru.DepositPageObject;
import pageOjects.bankGuru.EditCustomerPageObject;
import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.LogInPageObject;
import pageOjects.bankGuru.NewCustomerPageObject;
import pageOjects.bankGuru.RegisterPageObject;
import pageOjects.bankGuru.WithDrawPageObject;

public class Login_02_Register_And_Login_DriverLifeCycle {

	WebDriver driver;
	LogInPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	DeleteCustomerPageObject deleteCustomerPage;
	EditCustomerPageObject editCustomerPage;
	DepositPageObject depositPage;
	WithDrawPageObject withdrawPage;
	BalancePageObject balancePage;
	NewCustomerPageObject newCustomerPage;

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
		// driver.get("http://demo.guru99.com/V4/");
		// loginPage = new LogInPageObject(driver);

		loginPage = PageGeneratorManager_BankGuRu.getLoginPage(driver);

		loginPageURL = loginPage.getLoginPageURL();

	}

	@Test
	public void TC_01_Register() {
		// Click here link -> Open Register page
		registerPage = loginPage.clickToHereLink();
		registerPage = PageGeneratorManager_BankGuRu.getRegisterPage(driver);

		registerPage.inputToEmailTextbox("quintus" + randomNumber() + "@gmail.com");
		registerPage.clickToSubmitButton();
		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();

		// Step 1: From Register page-> open Login page
		loginPage = registerPage.openLoginPage(loginPageURL);

	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);
		homePage = loginPage.clickToLoginButton();

		// // Step 1: From manager/home page-> open edit page
		// postsPage = (PostsPageObject) dashboardPage.clickToDyamicAFewPageMenu(driver, "post");
		editCustomerPage = (EditCustomerPageObject) homePage.clickToDyamicAFewPageMenu_Guru(driver, "Edit Customer");

		newCustomerPage = (NewCustomerPageObject) editCustomerPage.clickToDyamicAFewPageMenu_Guru(driver, "New Customer");
		//homePage = (HomePageObject) newCustomerPage.clickToDyamicAFewPageMenu_Guru(driver, "Manager");
		editCustomerPage = (EditCustomerPageObject) newCustomerPage.clickToDyamicAFewPageMenu_Guru(driver, "Edit Customer");
		homePage = (HomePageObject) editCustomerPage.clickToDyamicAFewPageMenu_Guru(driver, "Manager");
		// driver.findElement(By.xpath("//iframe[@id='flow_close_btn_iframe']")).click();
		// driver.switchTo().defaultContent();
		// depositPage = editCustomerPage.openDepositPage(driver);

		// homePage = depositPage.openHomePage(driver);

		// Step 2: From edit page-> open delete customer page
		// deleteCustomerPage = editCustomerPage.openDeleteCustomerPage(driver);
		// // Step 3: From delete customer page-> open withdrawal page
		// withdrawPage = deleteCustomerPage.openWithDrawPage(driver);
		// // Step4: From withdrawal page-> open home page
		//
		// homePage = withdrawPage.openHomePage(driver);
		// // Step6: From manager/home customer page-> open balance page
		// balancePage = homePage.openBalancePage(driver);

		// Assert.assertTrue(homePage.isWelcomeMessageDisplayed());

	}

	public void TC_03_LogOut() {
		homePage.logOutHomePage();

		// Assert.assertEquals("You Have Succesfully Logged Out!!", (driver));
		// Assert.assertEquals(driver.getCurrentUrl(), "http://demo.guru99.com/V4/");
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
