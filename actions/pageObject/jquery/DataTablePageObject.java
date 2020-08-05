package pageObject.jquery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.jquery.DataTableJQueryUI;

public class DataTablePageObject extends AbstractPage {
	WebDriver driver;

	public DataTablePageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public void inputToColumnByName(String columnName,String value) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_HEADER_COLUMN_NAME, columnName);
		senkeyToElement(driver, DataTableJQueryUI.DYNAMIC_HEADER_COLUMN_NAME, value, columnName);
		sendKeyboardToElement(driver, DataTableJQueryUI.DYNAMIC_HEADER_COLUMN_NAME, Keys.ENTER, columnName);
	}
	public boolean isOneRowDisplayed(String textvalue) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_ONE_ROW_TEXT, textvalue);
		int rowNumber = countElementNumber(driver, DataTableJQueryUI.DYNAMIC_ONE_ROW_TEXT, textvalue);
		boolean status = isElementDisplayed(driver, DataTableJQueryUI.DYNAMIC_ONE_ROW_TEXT, textvalue);
		
		return rowNumber==1 && status;
		
	}
	public void clickToDynamicIconByCountryName(String iconName, String countryName) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_ACTION_ICON_BY_COUNTRY, countryName, iconName);
		clickToElement(driver, DataTableJQueryUI.DYNAMIC_ACTION_ICON_BY_COUNTRY, countryName, iconName);
		sleepInSecond(2);
	}
	public void navigateToPageByIndex(String pageIndex) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_PAGE_BY_INDEX, pageIndex);
		clickToElement(driver,  DataTableJQueryUI.DYNAMIC_PAGE_BY_INDEX, pageIndex);
		
		
	}
	public boolean ispageActiveByIndex(String pageIndex) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_PAGE_ACTIVE_BY_INDEX, pageIndex);
		return isElementDisplayed(driver, DataTableJQueryUI.DYNAMIC_PAGE_ACTIVE_BY_INDEX, pageIndex);
	}
	public void InputToDynamicTextboxAtRowNumber(String columnName, String rowNumber,String inputValue) {
		waitForElementVisible(driver, DataTableJQueryUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName);
		int columnPosition= countElementNumber(driver, DataTableJQueryUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName)+1;
		System.out.println("column name = "+columnName+ " at position = "+columnPosition);
		senkeyToElement(driver, DataTableJQueryUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX, inputValue, rowNumber, Integer.toString(columnPosition));
	sleepInSecond(2);
	}
}
