package tests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.FilterProducts;

public class FilterProductsTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private FilterProducts filterProducts;

	@BeforeTest
	public void setup() throws InterruptedException {
		filterProducts = new FilterProducts(driver, wait) ;
		driver = filterProducts.chromeDriverConnection();
		filterProducts.visit("https://www.hoodies.co.il/");
	}

	@Test
	public void filterProductsTest() throws InterruptedException {
		filterProducts.searchProduct("shirt");
	}

	@AfterTest
	public void endTest() throws InterruptedException {
		Thread.sleep(3000);
        driver.quit();
	}

}
