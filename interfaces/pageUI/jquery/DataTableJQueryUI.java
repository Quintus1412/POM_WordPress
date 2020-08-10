package pageUI.jquery;

public class DataTableJQueryUI {
	public static final String DYNAMIC_HEADER_COLUMN_NAME ="//div[text()='%s']/parent::div/following-sibling::input";
	public static final String DYNAMIC_ONE_ROW_TEXT ="//tr[not(@style='display: none;')]//td[text()='%s']";
	public static final String DYNAMIC_ACTION_ICON_BY_COUNTRY ="//td[@data-key='country' and text()='%s']/preceding-sibling::td[@class='qgrd-actions']/button[contains(@class,'%s')]";
	public static final String DYNAMIC_PAGE_BY_INDEX ="//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String DYNAMIC_PAGE_ACTIVE_BY_INDEX ="//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	
	
	public static final String DYNAMIC_COLUMN_POSITION_INDEX ="//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX ="//tr[%s]//td[%s]/input";


	

}
