package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogSearchPage extends Base {

	public CatalogSearchPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	private static final String SORT_PRICE_LOW_HIGH_VAL = "low_to_high";
	private static final String URL_SORT_ORDER_LOW_HIGH = "product_list_order=low_to_high";

	private By sorterSelect = By.id("sorter");
	private By productFinalPrice = By.cssSelector("span[data-price-type='finalPrice'] > .price");

	public void sortProductsByPriceLowToHigh() {
		selectFromDropDownListByValue(sorterSelect, SORT_PRICE_LOW_HIGH_VAL);
		waitUntilPageUrlContainsText(URL_SORT_ORDER_LOW_HIGH);
	}

	private List<Double> getProductFinalPrices() {
		List<WebElement> finalPriceElements = findElements(productFinalPrice);
		List<Double> finalPrices = new ArrayList<>();
		for (WebElement element : finalPriceElements) {
			String price = element.getText();
			price = price.substring(0, price.length() - 2);
			finalPrices.add(Double.parseDouble(price));
		}
		return finalPrices;
	}

	private boolean isListSortedAscending(List<Double> list) {
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i - 1) > list.get(i)) {
				return false;
			}
		}
		return true;
	}

	public boolean isProductListSortedByPriceLowHigh() {
		List<Double> prices = getProductFinalPrices();
		return isListSortedAscending(prices);
	}

}
