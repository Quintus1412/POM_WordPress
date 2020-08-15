package com.nopcommerce.testdata;

import commons.DataHelper;

public class EndUser {
	
	public static DataHelper data = DataHelper.getData();

	
	public static class Register {
		public static final String FIRST_NAME = data.getFirstName() ;
		public static final String LAST_NAME = data.getLastName();
		public static final String COMPANY_NAME =data.getCompanyName();
		
		public static final String PASSWORD = data.getPassword();
	
	
}
	public static class Login{
		
	}
	
	public static class Payment{
		
	}
	
	
}
