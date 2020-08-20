package pageObject.sortlab;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.sortlab.SortPageUI;

public class SortPageObject extends AbstractPage {

	WebDriver driver;
	public SortPageObject(WebDriver mapdriver) {
		driver=mapdriver;
	}
	public void selectItemInSortDropDown( String value) {
		waitForElementClickable(driver, SortPageUI.SORT_DRODOWN);
		highlightElement(driver, SortPageUI.SORT_DRODOWN);
		selectValueInDropdown(driver, SortPageUI.SORT_DRODOWN, value);
		
	}
	public boolean isNameSortedAscending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_NAME);
		return isDataSortedAscending(driver, SortPageUI.PRODUCT_NAME);
	}
	public boolean isNameSortedDescending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_NAME);
		return isDataSortedDescending(driver, SortPageUI.PRODUCT_NAME);
	}
	public boolean isPriceSortedAscending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_PRICE);
		return isPriceSortedAscending(driver, SortPageUI.PRODUCT_PRICE);
	}
	public boolean isPriceSortedDescending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_PRICE);
		return isPriceSortedDescending(driver, SortPageUI.PRODUCT_PRICE);
	}

}
