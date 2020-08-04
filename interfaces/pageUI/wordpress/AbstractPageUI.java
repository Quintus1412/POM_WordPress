package pageUI.wordpress;

public class AbstractPageUI {
	
	public static final String POSTS_LINK = "//a[contains(@class,'menu-icon-post')]";
	public static final String MEDIA_LINK = "//a[contains(@class,'menu-icon-media')]";
	public static final String PAGES_LINK = "//a[contains(@class,'menu-icon-page')]";
	public static final String DASHBOARD_LINK = "//a[contains(@class,'menu-icon-dashboard')]";
	
	
	//Dynamic locator
	public static final String DYMANIC_PAGE_LINK = "//a[contains(@class,'menu-icon-%s')]";
	}

