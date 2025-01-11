package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortProducts extends Base{

	public SortProducts(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

    private By colorSelect = By.cssSelector("[data-filter=\"color_group\"]>div:first-child");
    private By colorList = By.cssSelector(".items.swatch-attribute.type-swatch.type-swatch-color_group li");
    private By sizeSelect = By.cssSelector("[data-filter=\"size\"]>div:first-child");
    private By sizeList = By.cssSelector("[data-filter=\"size\"]>div:nth-child(2) li");
    private By sortBy = By.id("sorter");
    private By searchResultList = By.cssSelector(".products_container>div ul");
    private By resultItemName = By.cssSelector("[.products_container>div ul h3]");
    private By resultItemImage = By.cssSelector("[.products_container>div ul img]");
    
    public void filterByColor() {
    	//select color filter
    	waitUntilElementLocated(colorSelect);
    	findElement(colorSelect);
    	if (isDisplayed(colorSelect)) {
    		click(colorSelect);
    	}
    	
    	System.out.println("1================================");
    	System.out.println("Color is: \n"+getText(colorSelect));
    	
//    	System.out.println(getText(colorList));
    	
    	waitUntilElementLocated(colorList);
    	if (isDisplayed(colorList)) {
    		
    		clickElementInList(colorList, 3); //Black color
    		System.out.println("\n Success Mirit is displayed colorlist\n");
    	}
    	
    	//select size filter
    	waitUntilElementLocated(sizeSelect);
    	findElement(sizeSelect);
    	if (isDisplayed(sizeSelect)) {
    		click(sizeSelect);
    		System.out.println("\n Success is displayed sizeSelect\n");
    	}
    	
    	System.out.println("Size is: \n"+getText(sizeSelect));
    	System.out.println("2================================\n");
    	
    	
    	
    	waitUntilElementLocated(sizeList);	
    	if(isDisplayed(sizeList)){
    		System.out.print("size: "+getText(sizeList));
    		clickElementInList(sizeList, 2); //Small size
    	}
    	
    	System.out.println(getText(sizeList)+"***********");
    	
    	//sort by name
    	waitUntilElementLocated(sortBy);
    	findElement(sortBy);
    	selectFromDropDownListByValue(sortBy, "name");
    	
    	resultValidation();
    	
/*
	private By searchButton = By.cssSelector("[class='action header-search-toggle']");
	private By searchField = By.id("header-search-input");
    
	public void searchProduct(String searchInput) {
		click(searchButton);
    	type(searchField, searchInput + Keys.ENTER);
*/
    }

    
    public void resultValidation() {
    	
    	waitUntilElementLocated(searchResultList);
    	System.out.println("Validation:");
    	System.out.println(findElements(resultItemName));
    	
    	//save all results to list array
    	
    //	nameList[]= createList(findElements(resultItemName));
    	
    	//send to API for validation
    	//api(nameList,searchInput)
    	
    }
/*
    public Array createList(<webElement> list) {
    	
    	Array[] array = new Array();
    	
    	for (int i =0; i< list.length(); i++) {
			array[i]= list[i];
		}
    }
    */
}