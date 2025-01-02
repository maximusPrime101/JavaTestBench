package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.time.Duration;
import java.util.List;

public class SearchByPhoto extends Base{

	public SearchByPhoto(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	private By photoIcon = By.cssSelector(".hoodies_logo button");
	private By openGallery = By.cssSelector("[data-action-type=\"open-camera\"]");
	//private By closePopUp = By.cssSelector("[data-role=\"closeBtn\"]");
	//String redPath = "C:\\Git\\JavaTestBench\\Final_Project\\Images_to_upload\\red.png";
	String redPath = System.getProperty("user.dir") +"\\Images_to_upload\\red.png";

	
	public void enterSearchByPhoto() throws InterruptedException{
		
		//if (findElement(closePopUp) != null) {
		//	click(closePopUp);
	//	}
		click(photoIcon);
		click(openGallery);
		
		try {
			uploadFile(redPath);
		} catch (AWTException e) {
			e.printStackTrace();
			System.out.println("File upload failed.");
		}
	}
	
	
}
