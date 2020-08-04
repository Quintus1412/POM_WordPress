package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_BankGuRu;
import pageUI.bankGuru.HomePageUI;

public  class EditCustomerPageObject extends AbstractPage{
	WebDriver driver;
	
	public EditCustomerPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at edit customer page "+ driver.toString());
	}

	
}
