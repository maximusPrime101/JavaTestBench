package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SortProducts;
import pages.NavigationBar;

public class SortProductsTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private SortProducts filterProducts;
	private NavigationBar navigationBar;

	@BeforeTest
	public void setup() {
		filterProducts = new SortProducts(driver, wait);
		navigationBar =  new NavigationBar(driver, wait);
		driver = filterProducts.chromeDriverConnection();
		filterProducts.visit("https://www.hoodies.co.il/");
	}

	@Test
	public void filterProductsTest() {
		navigationBar.searchProduct("shirt");
	}

	@AfterTest
	public void endTest() {
		driver.quit();
	}

}
