package pageUI.wordpress.admin;

public class AbstractPageUI {
	
	public static final String POSTS_LINK = "//a[contains(@class,'menu-icon-post')]";
	public static final String MEDIA_LINK = "//a[contains(@class,'menu-icon-media')]";
	public static final String PAGES_LINK = "//a[contains(@class,'menu-icon-page')]";
	public static final String DASHBOARD_LINK = "//a[contains(@class,'menu-icon-dashboard')]";
	public static final String PLANS_LINK = "//div[@class='wp-menu-name' and text()='Plans']";
	
	
	//Dynamic locator
	public static final String DYMANIC_PAGE_LINK = "//div[contains(text(),'%s')]";
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String UPLOADING_PROGRESS_ICON="//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String UPLOADED_IMAGE="//div[@class='thumbnail']//img";

	public static final String DYNAMIC_SUCCESS_MESSAGE_ON_POST_OR_PAGES_PAGE="//div[@id='message']/p[contains(text(),'%s')]";
	
	public static final String DYNAMIC_ROW_VALUE_AT_COLUM_NAME ="//td[@data-colname='%s']//a[text()='%s']";
	
	
	}

