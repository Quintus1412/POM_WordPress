package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class BalancePageObject extends AbstractPage {
	WebDriver driver;
	public BalancePageObject (WebDriver mapDriver)
	{
		driver = mapDriver;
		System.out.print("Driver at balance page "+ driver.toString());
	}
	

}
