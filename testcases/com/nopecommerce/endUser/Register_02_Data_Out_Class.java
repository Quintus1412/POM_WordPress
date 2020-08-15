package com.nopecommerce.endUser;

import org.testng.annotations.Test;

import com.nopcommerce.testdata.EndUser;

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

public class Register_02_Data_Out_Class extends AbstractTest {

	WebDriver driver;
	DataHelper data;

	String email = "emailrondom."+randomNumber()+"@gmail.com";
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		data = DataHelper.getData();
		



	}

	@Test
	public void TC_01_RegisterToSystem() {
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(EndUser.Register.FIRST_NAME);
		driver.findElement(By.id("LastName")).sendKeys(EndUser.Register.LAST_NAME);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(EndUser.Register.COMPANY_NAME);

		driver.findElement(By.id("Password")).sendKeys(EndUser.Register.PASSWORD);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(EndUser.Register.PASSWORD);
		driver.findElement(By.id("register-button")).click();

	}

	
	public void Verify_My_Account() {

	}

	@AfterClass
	public void afterClass() {

	}

}
