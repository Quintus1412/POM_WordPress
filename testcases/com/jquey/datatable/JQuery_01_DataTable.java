package com.jquey.datatable;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
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
import pageObjects.wordpress.DashBoardPageObject;
import pageObjects.wordpress.LogInPageObject;
import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;

public class JQuery_01_DataTable extends AbstractTest {

	WebDriver driver;
	//driver is undefined = xxxxx-xxx-xx-xxx-xxxx
	DataTablePageObject datatablePage;
	

	DriverManager driverManager;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getDriverManager(browserName);
		/// driver = 1234-56245-145-6524
		driver = driverManager.getDriver("https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");	
		// Open URL -> navigate to login page
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
	
	public void TC_03_Paging_By_Index() {
		datatablePage.refresh(driver);
		datatablePage.navigateToPageByIndex("6");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("6"));
		datatablePage.navigateToPageByIndex("5");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("5"));
		datatablePage.navigateToPageByIndex("4");
		Assert.assertTrue(datatablePage.ispageActiveByIndex("4"));
		
		
	}
	@Test
	public void TC_04_DynamicRow() {
	//	datatablePage.openURL(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/" );
		
		datatablePage.InputToDynamicTextboxAtRowNumber("Company", "2", "data 1");
		datatablePage.InputToDynamicTextboxAtRowNumber("Contact Person", "1", "data 1");
	datatablePage.InputToDynamicTextboxAtRowNumber("Order Placed", "2", "5");
	
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
