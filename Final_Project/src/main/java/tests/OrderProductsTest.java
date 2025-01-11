package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CheckoutPage;
import pages.NavigationBar;
import pages.ProductPage;
import pages.WomenClothesPage;
import utils.PageConstants.ProductColor;
import utils.PageConstants.ProductSize;

public class OrderProductsTest {
	private WebDriver driver;
	private WebDriverWait wait;
	private CheckoutPage checkoutPage;
	private NavigationBar navigationBar;
	private WomenClothesPage womenClothesPage;
	private ProductPage productPage;

	@BeforeTest
	public void setup() {
		checkoutPage = new CheckoutPage(driver, wait);
		driver = checkoutPage.chromeDriverConnection();
		navigationBar = new NavigationBar(driver, wait);
		checkoutPage.visit("https://www.hoodies.co.il/");
	}

	private void initWomenClothesPage() {
		navigationBar.enterWomenCategory();
		womenClothesPage = new WomenClothesPage(driver, wait);
	}

	private void initProductPage() {
		productPage = new ProductPage(driver, wait);
	}

	@Test
	public void testGuestOrderProduct_UntilPayPal() {
		initWomenClothesPage();
		womenClothesPage.selectHoodiesCategory();
		womenClothesPage.clickProductFromList(1);
		initProductPage();
		productPage.selectColor(ProductColor.LIGHT_BLUE);
		productPage.selectSize(ProductSize.M);
		productPage.increaseQuantityByOne();
		productPage.addToCart();
		navigationBar.waitForCartUpdate();
		navigationBar.openMiniShoppingCart();
		navigationBar.proceedToCheckout();
		checkoutPage.continueAsGuest();
		checkoutPage.selectDeliveryToAddress();
		checkoutPage.nextStepAfterShippingMethod();
		checkoutPage.fillAddressDetails("John", "Doe", "john_doe@gmail.com", "0527856448", "נתיבות", "הרצל", "10", "1",
				"Call before delivery");
		checkoutPage.selectAgreeToTermsAndConditions();
		checkoutPage.nextStepAfterShippingInfo();
		checkoutPage.selectPayWithPayPal();
	}

	@AfterTest
	public void endTest() {
		// driver.quit();
	}
}
