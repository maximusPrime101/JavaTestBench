package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar extends Base{
	
	public NavigationBar(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By searchButton = By.className("header-search-toggle");
    private By inputField = By.id("header-search-input");
    private By cartButton = By.className("showcart");
    private By miniCartCheckoutButton = By.className("minicart-to-checkout-button");
    private By womenClothesLink = By.cssSelector("a[href='/women']");
    private By shoppingCartCounter = By.cssSelector("[class='counter qty _block-content-loading']");
    
    public void searchProduct(String searchInput) {
		click(searchButton);
    	type(inputField, searchInput).sendKeys(Keys.ENTER);
    }
    
    public void openMiniShoppingCart() {
    	click(cartButton);
    }
    
    public void proceedToCheckout() {
    	click(miniCartCheckoutButton);
    }
    
    public void enterWomenCategory() {
    	click(womenClothesLink);
    }
    
    public void waitForCartUpdate() {
    	waitUntilAttributeValueChange(shoppingCartCounter, "data-count", "0", Duration.ofSeconds(3));
    }
    
}
