package com.nopecommerce.endUser;

import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.AbstractTest;
import commons.DataHelper;
import commons.PageGeneratorManager_JQuery;
import pageObject.jquery.DataTablePageObject;
import pageObject.sortlab.SortPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Register_01_Data_In_Class extends AbstractTest {

	WebDriver driver;
	DataHelper data;

	String firstname, lastname, email, companyName, password;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		data = DataHelper.getData();
		

		password = data.getPassword();
		firstname = data.getFirstName();
		lastname = data.getLastName();
		email = data.getEmail();
		companyName = data.getCompanyName();


	}

	@Test
	public void TC_01_RegisterToSystem() {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		verifyTrue(false);

		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

	}

	@Test
	public void TC_02_Verify_My_Account() {
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstname);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastname);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"),email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);
		
	}

	@AfterClass
	public void afterClass() {
closeBrowserAndDriver(driver);
	}

}
