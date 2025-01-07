package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.awt.AWTException;

public class SearchByPhoto extends Base {

    private By photoIcon = By.cssSelector(".hoodies_logo button");
    private By openGallery = By.cssSelector("[data-action-type=\"open-camera\"]");
    private By closePopUp = By.cssSelector("[data-role=\"closeBtn\"]");

    private GeminiApiService geminiApiService;

    private String promptPath = "prompts/Search_by_photo.txt";
    private String imagePath = "images_to_upload/two-fashion-models2.png";
    private String outputDir = "Search_by_photo";

    public SearchByPhoto(WebDriver driver, WebDriverWait wait, GeminiApiService geminiApiService) {
        super(driver, wait);
        this.geminiApiService = geminiApiService;
    }

    public void enterSearchByPhoto() throws InterruptedException, IOException {
        if (findElement(closePopUp) != null) {
            click(closePopUp);
        }

        click(photoIcon);
        click(openGallery);

        try {
            uploadFile(imagePath);
        } catch (AWTException e) {
            e.printStackTrace();
            System.out.println("File upload failed.");
        }

        // Delegate API call to GeminiApiService
        callGeminiApi();
    }

    public void callGeminiApi() {
        try {
            // Delegate the logic to the GeminiApiService
            String response = geminiApiService.callGeminiApi(promptPath, imagePath, outputDir);

            // Handle the response
            System.out.println("SearchByPhoto\nAPI Response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
