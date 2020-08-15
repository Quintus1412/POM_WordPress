package com.nopecommerce.endUser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.testdata.EndUser;
import com.nopcommerce.testdata.EndUserJson;

import commons.AbstractTest;
import commons.GlobalConstants;

public class Register_04_Data_Driven extends AbstractTest {

	WebDriver driver;
	
	EndUserJson data;
String firstname, lastname, companyName, password;
	String email = "emailrondom."+randomNumber()+"@gmail.com";
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		data = EndUserJson.getEndUserData(GlobalConstants.ROOT_FOLDER+"\\testdata\\com\\nopcommerce\\testdata\\EndUser.json");
		

		password = data.getPassword();
		firstname = data.getFirstName();
		lastname = data.getLastName();
		email = data.getEmail()+randomNumber()+"@hotmail.com";
		companyName = data.getCompanyName();



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
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstname);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastname);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"),email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);
	}

	@AfterClass
	public void afterClass() {

	}

}
