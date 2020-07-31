package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

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

public class Login_03_Apply_AbstractPage_Extend extends AbstractPage {

	WebDriver driver;

	String emailTextbox = "//input[@id='usernameOrEmail']";
	String passwordTextbox = "//input[@id='password']";
	String loginButton = "//button[@class='button form-button is-primary']";
	String errorMessage = "//div[@class='form-input-validation is-error']/span";
	String errorMessagePassword = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		openURL(driver, "https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void TC_01_EmptyEmail() {
		senkeyToElement(driver, emailTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementtext(driver, errorMessage), "Please enter a username or email address.");

	}

	@Test
	public void TC_02_InvalidEmail() {
		senkeyToElement(driver, emailTextbox, "123@123.123");
		clickToElement(driver, loginButton);
		Assert.assertEquals(getElementtext(driver, errorMessage), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void TC_03_EmailNotExisted() {
		senkeyToElement(driver, emailTextbox, "automation" + randomNumber() + "@gmail.com");
		clickToElement(driver, loginButton);
		Assert.assertEquals(getElementtext(driver, errorMessage), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void TC_04_EmptyPass() {
		senkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);
		senkeyToElement(driver, passwordTextbox, "");
		clickToElement(driver, loginButton);

		Assert.assertEquals(getElementtext(driver, errorMessagePassword), "Don't forget to enter your password.");
	}

	@Test
	public void TC_05_PassLessThan6Characters() {
		senkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);
		senkeyToElement(driver, passwordTextbox, "1234");
		clickToElement(driver, loginButton);
		Assert.assertEquals(getElementtext(driver, errorMessagePassword), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_06_IncorrectPass() {
		senkeyToElement(driver, emailTextbox, "automationeditor");
		clickToElement(driver, loginButton);
		senkeyToElement(driver, passwordTextbox, "123456");
		clickToElement(driver, loginButton);
		Assert.assertEquals(getElementtext(driver, errorMessagePassword), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_07_ValidPass() {
		senkeyToElement(driver, emailTextbox, "automationeditor");

		clickToElement(driver, loginButton);
		senkeyToElement(driver, passwordTextbox, "automationfc");
		clickToElement(driver, loginButton);
		Assert.assertTrue(isElementDisplayed(driver, "//h1[contains(text(),'Dashboard')]"));
		Assert.assertTrue(isElementDisplayed(driver, "//div[@id='dashboard-widgets']"));
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
