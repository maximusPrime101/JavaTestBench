package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage extends Base{

	public ContactUsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By contactUsLink = By.cssSelector("[href='/contact_us']");
	private By pageTitle = By.cssSelector("h1[class='title']");
    private By firstNameField = By.name("first_name");
    private By lastNameField  = By.name("last_name");
    private By emailField = By.id("form.email");
    private By phoneField = By.name("phone");
    private By messageField = By.name("textarea");
    private By sendButton = By.name("send");
    private By sendSucessMsg = By.cssSelector("[class='idus_forms_message active']");
    
    public void visitContactUsPage() {
    	click(contactUsLink);
    }
    
    public String getPageTitle() {
    	return getText(pageTitle);
    }
    
    public void fillFirstName(String firstName) {
    	type(firstNameField, firstName);
    }
    
    public void fillLastName(String lastName) {
    	type(lastNameField, lastName);
    }
    
    public void fillEmail(String email) {
    	type(emailField, email);
    }
    
    public void fillPhone(String phone) {
    	type(phoneField, phone);
    }
    
    public void fillMessage(String msg) {
    	type(messageField, msg);
    }
    
    public void clickSend() {
    	click(sendButton);
    }
    
    public String getSendSuccessMsg () {
    	return getText(sendSucessMsg);
    }
    
    /*
    public void submitContactForm() {
    	click(contactUsLink);
    	Assert.assertEquals(getText(pageTitle), "יצירת קשר");
    	type(firstNameField, "John");
    	type(lastNameField, "Doe");
    	type(emailField, "john_doe@gmail.com");
    	type(phoneField, "0547762556");
        type(messageField, "Hello, this is a test. Thank you!");
        click(sendButton);
        Assert.assertEquals(getText(sendSucessMsg), "הפרטים נשלחו בהצלחה");
    }
    */

}
