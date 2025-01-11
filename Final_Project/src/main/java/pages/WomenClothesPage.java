package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WomenClothesPage extends Base{

	public WomenClothesPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By filterByCategoryTab = By.cssSelector(".filter-options-item.filter_cat");
	private By hoodiesCategoryButton = By.cssSelector("[attribute-code='cat'] [href='https://www.hoodies.co.il/women/hoodies']");
	private By productsList = By.cssSelector("[class='products list items product-items view_2'] li");
	
	public void selectHoodiesCategory() {
		click(filterByCategoryTab);
		click(hoodiesCategoryButton);
    }
	
	public void clickProductFromList(int index) {
		WebElement product = findElements(productsList).get(index);
		click(product);
	} 

}
