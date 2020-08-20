package com.retry.testfailed;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	@Test
	public void TC_01() {
	}
	
	@Test
	public void TC_02() {
	}
	
	@Test
	public void TC_03() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void TC_04() {
	}
	




}
