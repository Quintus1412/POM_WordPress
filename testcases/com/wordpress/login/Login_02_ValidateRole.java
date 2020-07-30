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

public class Login_02_ValidateRole {

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

	
	
	
	
	public int randomNumber() {
		Random number = new Random(9999);
		return number.nextInt();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
