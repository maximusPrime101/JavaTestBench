package tests;


import java.awt.AWTException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchByPhoto;


public class SearchByPhotoTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private SearchByPhoto searchByPhoto;
	
	
	@BeforeTest
	public void setup() throws InterruptedException{
		searchByPhoto = new SearchByPhoto(driver, wait);
		driver = searchByPhoto.chromeDriverConnection();
		searchByPhoto.visit("https://www.hoodies.co.il/");
	}
	
	@Test
	public void searchTest() throws InterruptedException,AWTException{

		searchByPhoto.enterSearchByPhoto();
		
		
	}

	@AfterTest
	public void endTest() {
		
	}
	
}
