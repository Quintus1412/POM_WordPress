package com.jquey.datatable;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import commons.AbstractTest;
import commons.VerificationFailures;
import commons.MethodListener;

public class Assert_Verify extends AbstractTest {

	SoftAssert soft;
	
	@Test
	public void TC_01() {
		System.out.println("Step 1-1");
		Assert.assertEquals("Automation", "Automation");
		
		System.out.println("Step 1-2");
		Assert.assertEquals("Automation", "Manual");
		
		System.out.println("Step 1-3");
		Assert.assertEquals("Automation", "Security");
		}
	@Test
	public void TC_02() {
	
		System.out.println("Step 2-1");
		soft.assertTrue(true);
		
		System.out.println("Step 2-2");
		soft.assertTrue(false);
		
		System.out.println("Step 2-3");
		soft.assertTrue(true);
		
		soft.assertAll();
	}
		
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
		//		log.info(" -------------------------- PASSED -------------------------- ");
			} else {
	//			log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
	//			log.info(" -------------------------- PASSED -------------------------- ");
			} else {
	//			log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		//	log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
		//	log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
		

}
