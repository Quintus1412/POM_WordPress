package commons;

import org.openqa.selenium.WebDriver;

import pageObject.jquery.DataTablePageObject;
import pageObjects.wordpress.PagesPageObject;



public class PageGeneratorManager_JQuery {
	public static DataTablePageObject getJQueryPage(WebDriver driver) {
		return new DataTablePageObject(driver);
	}
	
	
}
