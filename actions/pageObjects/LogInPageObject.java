package pageObjects;

import commons.AbstractPage;

public class LogInPageObject extends AbstractPage {
	public void inputToEmailTextbox() {
		waitForElementVisible();
		clickElement();
		
	}

}
