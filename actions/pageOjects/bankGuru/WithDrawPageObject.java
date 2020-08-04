package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithDrawPageObject extends AbstractPage {
	WebDriver driver;
	public WithDrawPageObject (WebDriver mapDriver)
	{
		driver = mapDriver;
		System.out.print("Driver at withdraw page "+ driver.toString());
	}
	
	

}
