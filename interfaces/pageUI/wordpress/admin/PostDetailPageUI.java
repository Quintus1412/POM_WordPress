package pageUI.wordpress.admin;

public class PostDetailPageUI {

	public static final String CATEGORY_NAME ="//p[@class='post-categories']/a[text()='%s']";	
	public static final String TITLE_NAME ="//h1[@class='post-title' and text()='%s']";	
	public static final String IMAGE_NAME ="//img[contains(@src,'%s')]";	
	public static final String POST_CONTENT_VALUE ="//div[@class='post-content']/p[contains(text(),'%s')]";	
	public static final String TAG_VALUE ="//a[contains(text(),'%s')]";	
	public static final String CREATED_DATE ="//span[@class='post-meta-date']/a[text()='%s']";	
	public static final String AUTHOR ="//span[@class='post-meta-author']/a[contains(text(),'%s')]";	
}
