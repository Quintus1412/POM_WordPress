package Browsers_Factory;

public class BrowserDriverFactory {
	public static DriverManager getDriverManager(String browserName) {
		DriverManager driverManager;
		switch (browserName) {
		case "chrome":
			driverManager = new Chrome_Driver_Manager();
			break;
		case "firefox":
			driverManager = new Firefox_Driver_Manager();
			break;
		default:
			driverManager = new ChromeHeadless_Driver_Manager();
			break;
		}
		return driverManager;

	}

}
