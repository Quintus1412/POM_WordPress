package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.admin.DashBoardPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailsPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;
import pageUI.wordpress.admin.AbstractPageUI;
import pageUI.wordpress.user.HomePageUI;

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

	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<String> arrayList = new ArrayList<String>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	public boolean isDataSortedDescending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<String> arrayList = new ArrayList<String>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(arrayList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}
		// Reverse data to sort DESC 
		Collections.reverse(arrayList);
		
		System.out.println(" ------------ Dữ liệu đã SORT DES trong Code: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}
		
		
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}
	
	public boolean isDateSortedAscending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<Date> arrayList = new ArrayList<Date>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
            //arrayList.add(Date.parse(element.getText().replace("/", "").trim()));
        }

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Date name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Date> sortedList = new ArrayList<>();
		for (Date child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (Date name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	
	
	public boolean isPriceSortedAscending (WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	public boolean isPriceSortedDescending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();
		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);
		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Reverse data to sort DESC 
		Collections.reverse(sortedList);
		
		System.out.println(" ------------ Dữ liệu đã SORT DES trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		
		
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
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
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(4);
		}
		else {
		findElementByXpath(driver, locator).click();
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		if(driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, castToObject(locator, values));
			sleepInSecond(4);
		}
		else {
		findElementByXpath(driver, castToObject(locator, values)).click();
		}
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);

	}

	public void senkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void senkeyToElement(WebDriver driver, String locator, String value, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
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

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FOLDER + file + "\n";

		}
		fullFileName = fullFileName.trim();
		senkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);

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

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		elements = findElementsByXpath(driver, castToObject(locator, values));
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
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

	public void uncheckToCheckbox(WebDriver driver, String locator, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (NoSuchElementException noSuchException) {
			noSuchException.printStackTrace();
			return false;
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		boolean status = true;
		try {
			element = driver.findElement(By.xpath(locator));
			if (element.isDisplayed()) {
				return status;
			}
		} catch (NoSuchElementException noSuchException) {
			noSuchException.printStackTrace();
			return false;
		}
		return status;
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = driver.findElements(By.xpath(castToObject(locator, values)));

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public void switchToFrameOrIframe(WebDriver driver, String locator) {
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

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
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

	public void scrollToTopPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,0)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");

	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 3px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, castToObject(locator, values));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, castToObject(locator, values)));
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

	public boolean isImageLoaded(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, castToObject(locator, values));
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined\' && arguments[0].naturalWidth >0", element);
		if (status) {
			return true;
		}
		return false;
	}

	public boolean waitForJStoLoad(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
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

	public boolean areFilesUploadedDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		System.out.println("the number of file is " + number);
		waitForElementsInvisible(driver, AbstractPageUI.UPLOADING_PROGRESS_ICON);
		sleepInSecond(5);

		elements = findElementsByXpath(driver, AbstractPageUI.UPLOADED_IMAGE);
		List<String> imageValues = new ArrayList<String>();
		int i = 0;
		for (WebElement image : elements) {
			System.out.println(image.getAttribute("src"));
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}
		for (String fileName : fileNames) {
			// Verify file name matching for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();

			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {

					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public static String splitAndConvertToLowerCase(String name) {
		String[] text = name.split("\\.");
		return text[0].toLowerCase();

	}
	// Apply for case: a few common pages (10-15-20 pages)
	// common Funtion of WORD PRESS project -> Open Page

	// Apply for case: A lots of common page (No specific object)
	public void openMenuPageByName(WebDriver driver, String menuName) {
		waitForElementClickable(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);
		clickToElement(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);

	}

	public AbstractPage openMenuPageByPageName(WebDriver driver, String menuName) {
		waitForElementClickable(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);
		clickToElement(driver, AbstractPageUI.DYMANIC_PAGE_LINK, menuName);

		if (menuName.equals("Posts")) {
			return PageGeneratorManager_WordPress.getPostsAdminPage(driver);
		} else if (menuName.equals("Media")) {
			return PageGeneratorManager_WordPress.getMediaAdminPage(driver);
		} else if (menuName.equals("Pages")) {
			return PageGeneratorManager_WordPress.getPagesAdminPage(driver);
		} else {
			return PageGeneratorManager_WordPress.getDashBoardAdminPage(driver);
		}
	}
	// Apply for case: A lots of common page (No specific object)

	public PostsPageObject clickToPostsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager_WordPress.getPostsAdminPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager_WordPress.getPagesAdminPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager_WordPress.getMediaAdminPage(driver);
	}

	public DashBoardPageObject clickToDashboardMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.DASHBOARD_LINK);
		clickToElement(driver, AbstractPageUI.DASHBOARD_LINK);
		return PageGeneratorManager_WordPress.getDashBoardAdminPage(driver);
	}

	public DashBoardPageObject openLogedinAdminPage(WebDriver driver) {
		openURL(driver, GlobalConstants.ADMIN_WORD_PRESS_URL);
		return PageGeneratorManager_WordPress.getDashBoardAdminPage(driver);

	}

	public HomePageObject openEndUserPage(WebDriver driver) {
		openURL(driver, GlobalConstants.USER_WORD_PRESS_URL);
		return PageGeneratorManager_WordPress.getHomeUserPage(driver);

	}

	public SearchResultPageObject inputToSearchTextboxEndUserPage(WebDriver driver, String searchValue) {
		scrollToTopPage(driver);
		waitForElementVisible(driver, AbstractPageUI.SEARCH_ICON);
		clickToElement(driver, AbstractPageUI.SEARCH_ICON);
		senkeyToElement(driver, AbstractPageUI.SEARCH_FIELD, searchValue);
		// click search
		clickToElement(driver, AbstractPageUI.INPUT_SEARCH_ICON);
		return PageGeneratorManager_WordPress.getSearchResultUserPage(driver);
	}

	public boolean isSuccessMessageDisplayedWithValue(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POST_OR_PAGES_PAGE, messageValue);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_SUCCESS_MESSAGE_ON_POST_OR_PAGES_PAGE, messageValue);
	}

	public boolean isRowValueDisplayedAtColumn(WebDriver driver, String columnName, String rowValue) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUM_NAME, columnName, rowValue);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUM_NAME, columnName, rowValue);
	}

	public boolean isRowValueUndisplayedAtColumn(WebDriver driver, String columnName, String rowValue) {
		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUM_NAME, columnName, rowValue);
	}

	public boolean isPostDisplayedOnLastedPost(WebDriver driver, String categoriesName, String postTitle, String dateCreated) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoriesName, postTitle, dateCreated);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoriesName, postTitle, dateCreated);
	}

	public boolean isPostUndisplayedOnLastedPost(WebDriver driver, String categoriesName, String postTitle, String dateCreated) {

		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoriesName, postTitle, dateCreated);
	}

	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver, String postTitle, String avatarImageName) {
		// waitForJStoLoad(driver);
		avatarImageName = avatarImageName.split("\\.")[0];

		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle, avatarImageName);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle, avatarImageName) && isImageLoaded(driver, AbstractPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle, avatarImageName);
	}

	public boolean isPostImageUndisplayedAtPostTitleName(WebDriver driver, String postTitle, String avatarImageName) {
		// waitForJStoLoad(driver);
		avatarImageName = avatarImageName.split("\\.")[0];
		return isElementUndisplayed(driver, AbstractPageUI.DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE, postTitle, avatarImageName);

	}

	public PostDetailsPageObject clickToPostDetailsWithTileName(WebDriver driver, String postTitle) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_TITLE, postTitle);
		clickToElementByJS(driver, AbstractPageUI.DYNAMIC_POST_TITLE, postTitle);
		return PageGeneratorManager_WordPress.getPostDetailUserPage(driver);
	}
	// Common Funtion of BANK GURU PROJECT

	private Select select;
	private Actions action;
	private WebElement element;
	private List<WebElement> elements;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;

}
