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

public class Login_02_Apply_AbstractPage_Init {
	AbstractPage abstPage;

	WebDriver driver;

	String emailTextbox = "//input[@id='usernameOrEmail']";
	String passwordTextbox = "//input[@id='password']";
	String loginButton = "//button[@class='button form-button is-primary']";
	String errorMessage = "//div[@class='form-input-validation is-error']/span";
	String errorMessagePassword = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
		// driver = new FirefoxDriver();
		//abstPage = new AbstractPage() ;
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@BeforeMethod
	public void beforeMethod() {
		
		abstPage.openURL(driver, "https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void TC_01_EmptyEmail() {
		// driver.findElement(emailTextboxBy).sendKeys("");
		
		abstPage.senkeyToElement(driver, emailTextbox, "");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);

		// Assert.assertEquals(driver.findElement(errorMessageBy).getText().trim(), "Please enter a username or email address.");
		Assert.assertEquals(abstPage.getElementtext(driver, errorMessage), "Please enter a username or email address.");

	}

	@Test
	public void TC_02_InvalidEmail() {
		// driver.findElement(emailTextboxBy).sendKeys("");
		abstPage.senkeyToElement(driver, emailTextbox, "123@123.123");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		Assert.assertEquals(abstPage.getElementtext(driver, errorMessage), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void TC_03_EmailNotExisted() {
		// driver.findElement(emailTextboxBy).sendKeys("automation" + randomNumber() + "@gmail.com");
		abstPage.senkeyToElement(driver, emailTextbox, "automation" + randomNumber() + "@gmail.com");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		Assert.assertEquals(abstPage.getElementtext(driver, errorMessage), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void TC_04_EmptyPass() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstPage.senkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("");
		abstPage.senkeyToElement(driver, passwordTextbox, "");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);

		Assert.assertEquals(abstPage.getElementtext(driver, errorMessagePassword), "Don't forget to enter your password.");
	}

	@Test
	public void TC_05_PassLessThan6Characters() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstPage.senkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("123");
		abstPage.senkeyToElement(driver, passwordTextbox, "1234");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		Assert.assertEquals(abstPage.getElementtext(driver, errorMessagePassword), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_06_IncorrectPass() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstPage.senkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("123456");
		abstPage.senkeyToElement(driver, passwordTextbox, "123456");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		Assert.assertEquals(abstPage.getElementtext(driver, errorMessagePassword), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_07_ValidPass() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		abstPage.senkeyToElement(driver, emailTextbox, "automationeditor");

		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("automationfc");
		abstPage.senkeyToElement(driver, passwordTextbox, "automationfc");
		// driver.findElement(loginButtonBy).click();
		abstPage.clickToElement(driver, loginButton);
		// Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed());
		Assert.assertTrue(abstPage.isElementDisplayed(driver, "//h1[contains(text(),'Dashboard')]"));
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dashboard-widgets']")).isDisplayed());
		Assert.assertTrue(abstPage.isElementDisplayed(driver, "//div[@id='dashboard-widgets']"));
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
