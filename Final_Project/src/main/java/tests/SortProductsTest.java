package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.CatalogSearchPage;
import pages.NavigationBar;

public class SortProductsTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private CatalogSearchPage catalogSearchPage;
	private NavigationBar navigationBar;

	@BeforeTest
	public void setup() {
		catalogSearchPage = new CatalogSearchPage(driver, wait);
		driver = catalogSearchPage.chromeDriverConnection();
		navigationBar =  new NavigationBar(driver, wait);
		catalogSearchPage.visit("https://www.hoodies.co.il/");
	}

	@Test
	public void filterProductsTest() {
		navigationBar.searchProduct("shirt");
		catalogSearchPage.sortProductsByPriceLowToHigh();
		boolean productsSorted = catalogSearchPage.isProductListSortedByPriceLowHigh();
		Assert.assertTrue(productsSorted, "Products list is not sorted by price in ascending order");
	}

	@AfterTest
	public void endTest() {
		catalogSearchPage.waitForDuration(Duration.ofSeconds(5));
		driver.quit();
	}

}
