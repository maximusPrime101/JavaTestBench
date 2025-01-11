package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends Base {

	public CheckoutPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	private By checkoutAsGuestLink = By.id("checkout-login-continue_as_guest");
	private By deliveryToLockersRadio = By.cssSelector("[for='shipping_method_pickup_orian_0']");
	private By citySelect = By.cssSelector("[pickup='city']");
	private By lockersSelect = By.cssSelector("[pickup='store']");
	private By deliveryToAddressRadio = By.cssSelector("[for='shipping_method_tapuz_tapuz_0']");
	private By afterShippingMethodButton = By.cssSelector("[step='shipping_method']");

	private By firstNameField = By.id("shipping_firstname");
	private By lastNameField = By.id("shipping_lastname");
	private By emailField = By.id("shipping_email");
	private By phoneField = By.id("shipping_telephone");
	private By cityField = By.id("shipping_city");
	private By streetField = By.id("shipping_street");
	private By houseField = By.id("shipping_number");
	private By apartmentField = By.id("shipping_apartment");
	private By commentsField = By.id("shipping.customer_notes");
	private By agreeToTermsRadio = By.cssSelector("[for='checkout_agreement']");
	private By afterShippingInfoButton = By.cssSelector("[step='shipping_info']");

	private By payCreditCardRadio = By.cssSelector("[for='payment_method_comax']");
	private By payPayPalRadio = By.cssSelector("[for='payment_method_paypal']");
	private By goToPayPalButton = By.cssSelector("[title='Go to PayPal']");

	public void continueAsGuest() {
		click(checkoutAsGuestLink);
	}

	public void selectDeliveryToLockers() {
		click(deliveryToLockersRadio);
	}

	public void selectDeliveryToAddress() {
		click(deliveryToAddressRadio);
		waitForDuration(Duration.ofSeconds(2));
	}

	public void selectCityByIndex(int cityIndex) {
		selectFromListByOptionIndex(citySelect, cityIndex);
	}

	public void selectLockerLocation(int lockerLocationIndex) {
		selectFromListByOptionIndex(lockersSelect, lockerLocationIndex);
	}

	public void nextStepAfterShippingMethod() {
		click(afterShippingMethodButton);
	}

	public void fillAddressDetails(String firstName, String lastName, String email, String phone, String city,
			String street, String house, String apartment, String comments) {
		
		typeAndMoveToNextField(firstNameField, firstName);
		typeAndMoveToNextField(lastNameField, lastName);
		typeAndMoveToNextField(emailField, email);
		typeAndMoveToNextField(phoneField, phone);
		type(cityField, city);
		waitForDuration(Duration.ofSeconds(2));
		typeAndMoveToNextField(streetField, street);
		typeAndMoveToNextField(houseField, house);
		typeAndMoveToNextField(apartmentField, apartment);
		type(commentsField, comments);
	}

	public void selectAgreeToTermsAndConditions() {
		waitUntilElementInteractable(agreeToTermsRadio);
		click(agreeToTermsRadio);
	}

	public void nextStepAfterShippingInfo() {
		waitUntilElementInteractable(afterShippingInfoButton);
		click(afterShippingInfoButton);
	}
	
	public void selectPayWithCreditCard() {
		click(payCreditCardRadio);
	}
	
	public void selectPayWithPayPal() {
		waitForDuration(Duration.ofSeconds(2));
		click(payPayPalRadio);
		click(goToPayPalButton);
	}
}
