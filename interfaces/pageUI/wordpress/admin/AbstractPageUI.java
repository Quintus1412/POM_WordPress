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
	public static final String DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE ="//p[@class='post-categories']/a[text()='%s']/parent::p/following-sibling::h2[@class='post-title']/a[text()='%s']/parent::h2/following-sibling::p[@class='post-meta']/a[text()='%s']";	
	public static final String DYNAMIC_POST_AVATAR_IMAGE_BY_TITLE ="//a[@title='%s']//img[contains(@src,'%s')]";	
	public static final String DYNAMIC_POST_TITLE ="//h2[@class='post-title']/a[text()='%s']";	
	public static final String SEARCH_ICON ="//a[@class='search-toggle']";	
	public static final String SEARCH_FIELD ="//input[@class='search-field']";	
	public static final String INPUT_SEARCH_ICON ="//span[@class='fa fw fa-search']";	
	public static final String NO_SEARCH_RESULT_TEXT ="//tr[@class='no-items']/td[text()='%s']";	
	
	}

