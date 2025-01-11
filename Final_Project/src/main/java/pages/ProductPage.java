package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.PageConstants.ProductColor;
import utils.PageConstants.ProductSize;

public class ProductPage extends Base{
	
	private static final Map<ProductColor, By> colorSelectorMap = Map.of(
			ProductColor.SALSA_RED, By.id("option-label-color-93-317560-product_page-item-172410"),
			ProductColor.LIGHT_BLUE, By.id("option-label-color-93-317560-product_page-item-171006"),
			ProductColor.CACTUS_GREEN, By.id("option-label-color-93-317560-product_page-item-170269")
	);
	
	private static final Map<ProductSize, By> sizeSelectorMap = Map.of(
			ProductSize.XS, By.cssSelector("div[title='XS']"),
			ProductSize.S, By.cssSelector("div[title='S']"),
			ProductSize.M, By.cssSelector("div[title='M']"),
			ProductSize.L, By.cssSelector("div[title='L']"),
			ProductSize.XL, By.cssSelector("div[title='XL']")
	);
	
	private By raiseQuantityButton = By.cssSelector("[class = 'arrow step-up']");
	private By lowerQuantityButton = By.cssSelector("[class = 'arrow step-down']");
	private By addToCartButton = By.id("product-addtocart-button");
	
	public ProductPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void selectSize(ProductSize size) {
		click(sizeSelectorMap.get(size));
	}
	
	public void selectColor(ProductColor color) {
		click(colorSelectorMap.get(color));
	}
	
	public void increaseQuantityByOne() {
		click(raiseQuantityButton);
	}
	
	public void decreaseQuantityByOne() {
		click(lowerQuantityButton);
	}
	
	public void addToCart() {
		click(addToCartButton);
	}

}
