package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterProducts extends Base{

	public FilterProducts(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By searchButton = By.cssSelector("[class='action header-search-toggle']");
	private By searchField = By.id("header-search-input");
    
	public void searchProduct(String searchInput) {
		click(searchButton);
    	type(searchField, searchInput + Keys.ENTER);
    }


}
