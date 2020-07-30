package com.wordpress.login;

import org.testng.annotations.Test;
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

public class Login_01_ValidateLogInForm {

	WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='usernameOrEmail']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By loginButtonBy = By.xpath("//button[@class='button form-button is-primary']");
	By errorMessageBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	By errorMessagePasswordBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	
	

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		 System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
				 driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.wordpress.com/wp-admin/");
	}

	@Test
	public void TC_01_EmptyEmail() {
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessageBy).getText().trim(), "Please enter a username or email address.");

	}

	@Test
	public void TC_02_InvalidEmail() {
		driver.findElement(emailTextboxBy).sendKeys("123@123.123");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessageBy).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void TC_03_EmailNotExisted() {
		driver.findElement(emailTextboxBy).sendKeys("automation"+randomNumber()+"@gmail.com");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessageBy).getText().trim(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void TC_04_EmptyPass() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessagePasswordBy).getText().trim(), "Don't forget to enter your password.");
	}

	@Test
	public void TC_05_PassLessThan6Characters() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessagePasswordBy).getText().trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_06_IncorrectPass() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(errorMessagePasswordBy).getText().trim(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void TC_07_ValidPass() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("automationfc");
		driver.findElement(loginButtonBy).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='dashboard-widgets']")).isDisplayed());
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
