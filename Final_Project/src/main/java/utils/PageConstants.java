package utils;

public class PageConstants {
	
	//Contact Us page constants:
	public static final String CONTACT_US_PAGE_HEADING = "יצירת קשר";
	public static final String CONTACT_FORM_SUBMIT_REQUEST_URL = "https://www.hoodies.co.il/forms/index/index/";
	
	public static final String SUCCESS_MSG_CONTACT_FORM_SUBMIT = "הפרטים נשלחו בהצלחה";
	
	public static final String ERR_MSG_CONTACT_FORM_EMPTY_FNAME = "* שם פרטי - שדה זה הוא חובה.";
	public static final String ERR_MSG_CONTACT_FORM_EMPTY_LNAME = "* שם משפחה - שדה זה הוא חובה.";
	public static final String ERR_MSG_CONTACT_FORM_EMPTY_EMAIL = "* אימייל - שדה זה הוא חובה.";
	public static final String ERR_MSG_CONTACT_FORM_EMPTY_PHONE = "* מספר נייד - שדה זה הוא חובה.";
	public static final String ERR_MSG_CONTACT_FORM_EMPTY_DESCRIPTION = "* תוכן הפנייה - שדה זה הוא חובה.";
	
	//Response codes:
	public static final int CONTACT_FORM_SUBMIT_RESPONSE_SUCCESS = 200;
	
	//Product colors:
	public enum ProductColor { 
		SALSA_RED,
		LIGHT_BLUE,
		CACTUS_GREEN,
		WHITE,
		BLACK 
	}
	
	public enum ProductSize {
		XS,
		S,
		M,
		L,
		XL
	}
}
