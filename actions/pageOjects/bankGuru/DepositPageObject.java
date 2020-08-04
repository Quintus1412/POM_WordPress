package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DepositPageObject extends AbstractPage {
WebDriver driver;
	
	public DepositPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at deposit  page "+ driver.toString());
	}




}
