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

		
		//Intercept logic:
				// Create DevTools session
				DevTools devTools = ((ChromeDriver) driver).getDevTools();
				devTools.createSession();
				devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

				final String REQUEST_URL = "https://www.hoodies.co.il/forms/index/index/";

				// TODO: intercept request
				// Listener to capture request events
				
				devTools.addListener(Network.requestWillBeSent(), requestSent -> {
					Request request = requestSent.getRequest();
					if (request.getUrl().equals(REQUEST_URL)) {
						System.out.println("Request URL: " + request.getUrl());
						System.out.println("Request Method: " + request.getMethod());
						System.out.println("Request Headers: " + request.getHeaders());
						if (request.getPostData().isPresent()) {
							System.out.println("Request Post Data: " + request.getPostData().get());
						}
					}
				});
				
				devTools.close();
		
		
		contactUsPage.clickSend();
		System.out.println("Sending Contact Us form");
		
	
		
		// Create DevTools session
				devTools = ((ChromeDriver) driver).getDevTools();
				devTools.createSession();
				devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
				
		// TODO: intercept response and check status code is 200
		// Check the response code
				devTools.addListener(Network.responseReceived(), responseReceived -> {
					Response response = responseReceived.getResponse();
					int status = response.getStatus();
					if (response.getUrl().equals(REQUEST_URL)) {
						System.out.println("Response URL: " + response.getUrl());
						System.out.println("Response Status: " + status);
						if (status == 200) {
							System.out.println("Form submission was successful with status code 200");
						} else {
							System.out.println("Form submission failed with status code " + status);
						}
					}
				});

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Clean up
		devTools.close();
	//end intercept logic
		
		
		

		Assert.assertEquals(contactUsPage.getSendSuccessMsg(), PageConstants.SUCCESS_MSG_CONTACT_FORM_SUBMIT,
				"Form submit success message is incorrect.");

		System.out.println("contact us form submitted successfully");

		System.out.println(testName + " test end");
	}

	@AfterTest
	public void endTest() throws InterruptedException {
		driver.quit();
	}
}