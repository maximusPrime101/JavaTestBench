package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

import pages.ContactUsPage;

public class ContactUsPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private ContactUsPage contactUsPage;

    @BeforeTest
    public void setup() throws InterruptedException {
    	//TODO: add logging
        contactUsPage = new ContactUsPage(driver, wait);
        driver = contactUsPage.chromeDriverConnection();
        contactUsPage.visit("https://www.hoodies.co.il/");
    }

    //TODO: add javadoc
    @Test
    public void testSubmitContactUsForm() throws InterruptedException {
    	//TODO: get inputs from excel
    	//TODO: add logging
    	contactUsPage.visitContactUsPage();
    	Assert.assertEquals(contactUsPage.getPageTitle(), "יצירת קשר", "Page title is incorrect.");
    	contactUsPage.fillFirstName("John");
    	contactUsPage.fillLastName("Doe");
    	contactUsPage.fillEmail("john_doe@gmail.com");
    	contactUsPage.fillPhone("0547762556");
    	contactUsPage.fillMessage("Hello, this is a test. Thank you!");
    	contactUsPage.clickSend();
    	Assert.assertEquals(contactUsPage.getSendSuccessMsg(), "הפרטים נשלחו בהצלחה", "Form submit success message is incorrect.");
    	//TODO: verify response status code is 200
    }

    @AfterTest
    public void endTest() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
