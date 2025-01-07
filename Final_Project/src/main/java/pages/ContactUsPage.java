package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import testdata.ContactFormData;

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
    
    public void fillAllFormFields(ContactFormData formInput) {
    	//TODO: validate formInput and its fields are not null
    	fillFirstName(formInput.getFirstName());
    	fillLastName(formInput.getLastName());
    	fillEmail(formInput.getEmail());
    	fillPhone(formInput.getPhone());
    	fillMessage(formInput.getMessage());
    }
    
    public String getSendSuccessMsg () {
    	return getText(sendSucessMsg);
    }
    
    
}