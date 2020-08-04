package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_BankGuRu;
import pageUI.bankGuru.HomePageUI;

public  class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;
	
	public NewCustomerPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at edit new customer page "+ driver.toString());
	}

	

	

		
}
