package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends Base{

	public CheckoutPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	By checkoutAsGuestLink = By.id("checkout-login-continue_as_guest");
	
	public void continueAsGuest() {
		click(checkoutAsGuestLink);
	}

}
