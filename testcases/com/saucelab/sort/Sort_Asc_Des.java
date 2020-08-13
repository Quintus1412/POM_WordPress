package com.saucelab.sort;

import org.testng.annotations.Test;

import Browsers_Factory.BrowserDriverFactory;
import Browsers_Factory.DriverManager;
import commons.AbstractTest;
import commons.PageGeneratorManager_JQuery;
import pageObject.jquery.DataTablePageObject;
import pageObject.sortlab.SortPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Sort_Asc_Des extends AbstractTest {

	WebDriver driver;
	// driver is undefined = xxxxx-xxx-xx-xxx-xxxx
	SortPageObject sortPage;

	DriverManager driverManager;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		sortPage = new SortPageObject(driver);

	}
@Test
	public void TC_01_SortProductNam() {
		sortPage.selectItemInSortDropDown("Name (A to Z)");
		verifyTrue(sortPage.isNameSortedAscending());

		sortPage.selectItemInSortDropDown("Name (Z to A)");
		verifyTrue(sortPage.isNameSortedDescending());

	}
@Test
	public void TC_02_SortPrice() {
		sortPage.selectItemInSortDropDown("Price (low to high)");
		verifyTrue(sortPage.isPriceSortedAscending());

		sortPage.selectItemInSortDropDown("Price (high to low)");
		verifyTrue(sortPage.isPriceSortedDescending());

	}

	public void TC_03_Paging_By_Index() {

	}

	@Test
	public void TC_04_DynamicRow() {
		

	}

	public int randomNumber() {
		Random number = new Random(9999);
		return number.nextInt();
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
