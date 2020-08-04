package pageOjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager_BankGuRu;
import pageUI.bankGuru.HomePageUI;

public  class DeleteCustomerPageObject extends AbstractPage{
	WebDriver driver;
	
	public DeleteCustomerPageObject (WebDriver mapDriver) {
		driver = mapDriver;
		System.out.print("Driver at delete customer page "+ driver.toString());
	}

	

	


}
