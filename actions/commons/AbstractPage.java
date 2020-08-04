package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.DashBoardPageObject;
import pageObjects.wordpress.MediaPageObject;
import pageObjects.wordpress.PagesPageObject;
import pageObjects.wordpress.PostsPageObject;
import pageOjects.bankGuru.BalancePageObject;
import pageOjects.bankGuru.DeleteCustomerPageObject;
import pageOjects.bankGuru.DepositPageObject;
import pageOjects.bankGuru.EditCustomerPageObject;
import pageOjects.bankGuru.HomePageObject;
import pageOjects.bankGuru.NewCustomerPageObject;
import pageOjects.bankGuru.WithDrawPageObject;
import pageUI.bankGuru.AbstractPageUI_BankGuRu;
import pageUI.wordpress.AbstractPageUI;

public abstract class AbstractPage {

	public void openURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public boolean isPageLoaded(WebDriver driver, String url) {
		String actualUrl = driver.getCurrentUrl();
		return actualUrl.equals(url);

	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();

	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();

	}

	public void senkeytAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);

	}

	public String getTextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();

	}

	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 10);
		explicitWait.until(ExpectedConditions.alertIsPresent());

	}

	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String windownTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if (title.equals(windownTitle)) {
				break;
			}
		}
	}

	public boolean areAllWindowsClosedExceptParent(WebDriver driver, String windownParent) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windownParent)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(windownParent);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}
		return false;

	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));

	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));

	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {

		findElementByXpath(driver, castToObject(locator, values)).click();
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);

	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public String getElementtext(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public void selectValueInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectIteamInCustomDropDownList(WebDriver driver, String ParentXpath, String allItemXpath, String expectedValue) {
		element = findElementByXpath(driver, ParentXpath);
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("argument[0].click();", element);
		sleepInSecond(1);
		explicitWait = new WebDriverWait(driver, 20);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(allItemXpath)));
		elements = findElementsByXpath(driver, allItemXpath);
		for (WebElement childElement : elements) {

			if (childElement.equals(expectedValue)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					jsExecutor.executeScript("argument[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					jsExecutor.executeScript("argument[0].click();", childElement);
				}
				sleepInSecond(1);
				break;
			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}
	public boolean isElementDisplayed(WebDriver driver, String locator, String ...values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public void switchToFrameOfIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElemet(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClick(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClick(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyBoard(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaSript);
	}

	public boolean isTextInInnerHTML(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");

	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined\' && arguments[0].naturalWidth >0", element);
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}

	// Apply for case: a few common pages (10-15-20 pages)
	// common Funtion of WORD PRESS project -> Open Page
	
	public AbstractPage clickToDyamicAFewPageMenu_Guru(WebDriver driver, String menuName) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.DYNAMIC_MENU, menuName);
		clickToElement(driver, AbstractPageUI_BankGuRu.DYNAMIC_MENU, menuName);
		
		if (menuName.equals("Edit Customer")) {
			return PageGeneratorManager_BankGuRu.getEditCustomerPage(driver);
		} else if (menuName.equals("New Customer")) {
			return PageGeneratorManager_BankGuRu.getNewCustomerPage(driver);
		} else if (menuName.equals("Delete Customer")) {
			return PageGeneratorManager_BankGuRu.getDeleteCustomerPage(driver);
		} else {
			return PageGeneratorManager_BankGuRu.getHomePage(driver);
		}
	}
	
	// Apply for case: A lots of common page (No specific object)
		public void clickToDyamicALotPagesMenu(WebDriver driver, String menuName) {
			waitForElementClickable(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);
			clickToElement(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);

			if (menuName.equals("post")) {
				 PageGeneratorManager_WordPress.getPostsPage(driver);
			} else if (menuName.equals("media")) {
				 PageGeneratorManager_WordPress.getMediaPage(driver);
			} else if (menuName.equals("page")) {
				 PageGeneratorManager_WordPress.getPagesPage(driver);
			} else {
				 PageGeneratorManager_WordPress.getDashBoardPage(driver);
			}
		}
		
		public AbstractPage clickToDyamicAFewPageMenu(WebDriver driver, String menuName) {
			waitForElementClickable(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);
			clickToElement(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);
			
			if (menuName.equals("post")) {
				return PageGeneratorManager_WordPress.getPostsPage(driver);
			} else if (menuName.equals("media")) {
				return PageGeneratorManager_WordPress.getMediaPage(driver);
			} else if (menuName.equals("page")) {
				return PageGeneratorManager_WordPress.getPagesPage(driver);
			} else {
				return PageGeneratorManager_WordPress.getDashBoardPage(driver);
			}
		}
	// Apply for case: A lots of common page (No specific object)
	public void clickToDyamicALotPagesMenu_Guru(WebDriver driver, String menuName) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.DYNAMIC_MENU, menuName);
		clickToElement(driver, AbstractPageUI_BankGuRu.DYNAMIC_MENU, menuName);
		
		if (menuName.equals("Edit Customer")) {
			PageGeneratorManager_BankGuRu.getEditCustomerPage(driver);
		} else if (menuName.equals("New Customer")) {
			PageGeneratorManager_BankGuRu.getNewCustomerPage(driver);
		} else if (menuName.equals("Delete Customer")) {
			PageGeneratorManager_BankGuRu.getDeleteCustomerPage(driver);
		} else {
			PageGeneratorManager_BankGuRu.getHomePage(driver);
		}
	}

	

	public PostsPageObject clickToPostsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager_WordPress.getPostsPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager_WordPress.getPagesPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager_WordPress.getMediaPage(driver);
	}

	public DashBoardPageObject clickToDashboardMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.DASHBOARD_LINK);
		clickToElement(driver, AbstractPageUI.DASHBOARD_LINK);
		return PageGeneratorManager_WordPress.getDashBoardPage(driver);
	}

	// Common Funtion of BANK GURU PROJECT
	public WithDrawPageObject openWithDrawPage(WebDriver driver) {
		// waitForElementClickable(driver, AbstractPageUI_BankGuRu.WITHDRAW_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.WITHDRAW_MENU);
		return PageGeneratorManager_BankGuRu.getWithdrawPage(driver);
	}

	public BalancePageObject openBalancePage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.BALANCE_ENQUIRY);
		clickToElement(driver, AbstractPageUI_BankGuRu.BALANCE_ENQUIRY);
		return PageGeneratorManager_BankGuRu.getBalancePage(driver);
	}

	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.EDIT_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.EDIT_CUSTOMER_MENU);
		return PageGeneratorManager_BankGuRu.getEditCustomerPage(driver);
	}

	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.NEW_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.NEW_CUSTOMER_MENU);
		return PageGeneratorManager_BankGuRu.getNewCustomerPage(driver);
	}

	public DepositPageObject openDepositPage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.DEPOSIT_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.DEPOSIT_MENU);
		return PageGeneratorManager_BankGuRu.getDepositerPage(driver);
	}

	public DeleteCustomerPageObject openDeleteCustomerPage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.DELETE_CUSTOMER_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.DELETE_CUSTOMER_MENU);
		return PageGeneratorManager_BankGuRu.getDeleteCustomerPage(driver);
	}

	public HomePageObject openHomePage(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI_BankGuRu.HOME_OR_MANAGER_MENU_MENU);
		clickToElement(driver, AbstractPageUI_BankGuRu.HOME_OR_MANAGER_MENU_MENU);
		return PageGeneratorManager_BankGuRu.getHomePage(driver);
	}

	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;


}
