package tests;


import java.io.IOException;
import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchByPhoto;
import pages.GeminiApiService;
import pages.GeminiApiClient;


public class SearchByPhotoTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private SearchByPhoto searchByPhoto;
	private GeminiApiService geminiApiService;

	
	@BeforeTest
	public void setup() throws InterruptedException{
		GeminiApiClient geminiApiClient = new GeminiApiClient();
        geminiApiService = new GeminiApiService(geminiApiClient);
		searchByPhoto = new SearchByPhoto(driver, wait, geminiApiService);
		driver = searchByPhoto.chromeDriverConnection();
	//	searchByPhoto.visit("https://www.hoodies.co.il/");
	}
	
	@Test
	public void searchTest() throws InterruptedException,AWTException,IOException {

	//	searchByPhoto.enterSearchByPhoto();
		searchByPhoto.callGeminiApi();
		
		
	}

	@AfterTest
	public void endTest() {
		driver.quit();
	}
	
}
