package tests;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.v130.network.model.Request;
import org.openqa.selenium.devtools.v130.network.model.Response;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

import pages.ContactUsPage;
import testdata.ContactFormData;
import utils.NetworkInterceptor;
import utils.PageConstants;

public class ContactUsPageTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private ContactUsPage contactUsPage;

	@BeforeTest
	public void setup() throws InterruptedException {
		// TODO: add logging
		contactUsPage = new ContactUsPage(driver, wait);
		driver = contactUsPage.chromeDriverConnection();
		contactUsPage.visit("https://www.hoodies.co.il/");
	}

	/**
	 * Submits the Contact Us form with valid input data and verifies successful
	 * submission.
	 * 
	 * @throws AssertionError if the page heading or the success message does not
	 *                        match the expected text.
	 */
	@Test
	public void testContactUsFormSubmit_ValidData_Succees() {
		// TODO: get inputs from excel
		// TODO: add logging
		final String testName = "testContactUsFormSubmit_ValidData_Success";
		System.out.println(testName + " test start");

		contactUsPage.visitContactUsPage();
		Assert.assertEquals(contactUsPage.getPageTitle(), PageConstants.CONTACT_US_PAGE_HEADING,
				"Page title is incorrect");

		ContactFormData contactFormData = new ContactFormData("John", "Doe", "john_doe@gmail.com", "0547762556",
				"Hello, this is a test. Thank you!");
		contactUsPage.fillAllFormFields(contactFormData);
		System.out.println("Filling contact form fields with input: " + contactFormData);

		NetworkInterceptor networkInterceptor = NetworkInterceptor.initNetworkInterceptor((ChromeDriver) driver);
		String requiredRequestUrl = PageConstants.CONTACT_FORM_SUBMIT_REQUEST_URL;
		networkInterceptor.interceptHttpRequestForUrl(requiredRequestUrl);
		
		contactUsPage.clickSend();
		System.out.println("Sending Contact Us form");

		networkInterceptor.interceptHttpResponseForUrl(requiredRequestUrl);
		
		networkInterceptor.closeDevToolsSession();
		
		int responseStatus = networkInterceptor.getResponse().getStatus();
		Assert.assertEquals(responseStatus, PageConstants.CONTACT_FORM_SUBMIT_RESPONSE_SUCCESS,
				"Contact Us form submit request supposed to return with: "
						+ PageConstants.CONTACT_FORM_SUBMIT_RESPONSE_SUCCESS + ", but returned with status "
						+ responseStatus);

		Assert.assertEquals(contactUsPage.getSendSuccessMsg(), PageConstants.SUCCESS_MSG_CONTACT_FORM_SUBMIT,
				"Form submit success message is incorrect.");
		
		System.out.println("Contact Us form submitted successfully");
		System.out.println(testName + " test end");
	}

	@AfterTest
	public void endTest() throws InterruptedException {
		driver.quit();
	}
}