package commons;

import org.openqa.selenium.WebDriver;

import pageOjects.bankGuru.BalancePageObject;
import pageOjects.bankGuru.DeleteCustomerPageObject;
import pageOjects.bankGuru.DepositPageObject;
import pageOjects.bankGuru.EditCustomerPageObject;
import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.LogInPageObject;
import pageOjects.bankGuru.NewCustomerPageObject;
import pageOjects.bankGuru.RegisterPageObject;
import pageOjects.bankGuru.WithDrawPageObject;

public class PageGeneratorManager_BankGuRu {
	public static LogInPageObject getLoginPage(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static WithDrawPageObject getWithdrawPage(WebDriver driver) {
		return new WithDrawPageObject(driver);
	}
	public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}
	public static DepositPageObject getDepositerPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}
	public static BalancePageObject getBalancePage(WebDriver driver) {
		return new BalancePageObject(driver);
	}
	public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
}
