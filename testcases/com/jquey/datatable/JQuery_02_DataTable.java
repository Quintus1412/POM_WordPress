package com.jquey.datatable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.AbstractPage;
import commons.AbstractTest;
import commons.PageGeneratorManager_JQuery;
import commons.PageGeneratorManager_WordPress;
import pageObject.jquery.DataTablePageObject;
import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.LogInPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;

public class JQuery_02_DataTable extends AbstractTest {

	WebDriver driver;
	//driver is undefined = xxxxx-xxx-xx-xxx-xxxx
	DataTablePageObject datatablePage;
	

	DriverManager driverManager;

	@Parameters({ "browser","url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		//driverManager = BrowserDriverFactory.getDriverManager(browserName);
		/// driver = 1234-56245-145-6524
		driver = getBrowserDriver(browserName, appUrl);

		datatablePage = PageGeneratorManager_JQuery.getJQueryPage(driver);
	}



	public void TC_01_LoginToSytem() {
		datatablePage.inputToColumnByName("Country","AFRICA" );
		Assert.assertTrue(datatablePage.isOneRowDisplayed("AFRICA"));
		datatablePage.refresh(driver);
		
		datatablePage.inputToColumnByName("Total","553353" );
		Assert.assertTrue(datatablePage.isOneRowDisplayed("553353"));
		
		
		//Assert.assertTrue(datatablePage.isOneRowDisplayed());
		
	}
	
	public void TC_02_Edit_Delate_Icon_By_Country_Name() {
		//
		//
		
		datatablePage.refresh(driver);
		datatablePage.clickToDynamicIconByCountryName("remove","Aruba");
		datatablePage.clickToDynamicIconByCountryName("remove","Arab Rep of Egypt");
		datatablePage.clickToDynamicIconByCountryName("remove","Angola");
		
		
	}
	@Test
	public void TC_03_Paging_By_Index() {
		datatablePage.refresh(driver);
		datatablePage.navigateToPageByIndex("6");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("6"));
		datatablePage.navigateToPageByIndex("5");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("5"));
		datatablePage.navigateToPageByIndex("4");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("4"));
	
		
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
