package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class Base {
	private WebDriver driver;
	private WebDriverWait wait;

	public Base(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver chromeDriverConnection() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		return driver;
	}

	public void waitUntilElementLocated(By locator) {
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilElementInteractable(By locator) {
		wait = new WebDriverWait(driver, Duration.ofMillis(4000));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement findElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public String getText(By locator) {
		return findElement(locator).getText();
	}

	public WebElement type(By locator, String inputText) {
		findElement(locator).sendKeys(inputText);
		return findElement(locator);
	}

	public WebElement typeAndMoveToNextField(By locator, String inputText) {
		return type(locator, inputText + Keys.TAB);
	}

	public void click(By locator) {
		findElement(locator).click();
	}

	public void click(WebElement element) {
		element.click();
	}

	public void clickElementInList(By locator, int indx) {
		findElements(locator).get(indx).click();
	}

	public boolean isDisplayed(By locator) {
		try {
			return findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void selectFromDropDownListByValue(By locator, String value) {
		Select select = new Select(findElement(locator));
		select.selectByValue(value);
	}

	public void selectFromListByOptionIndex(By locator, int index) {
		Select select = new Select(findElement(locator));
		List<WebElement> options = select.getOptions();
		try {
			options.get(index).click();
		} catch (Exception e) {
			throw new RuntimeException("No option found in select with index " + index);
		}
	}

	public void visit(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
	}

	public void waitUntilFieldIsFilled(By locator, Duration duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String value = findElement(locator).getDomProperty("value");
				try {
					// Wait for a brief moment to ensure typing has stopped
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Check if the value has not changed
				return value.equals(findElement(locator).getDomProperty("value"));
			}
		});
	}

	public void waitUntilFieldsAreFilled(List<By> fieldLocators, Duration duration) {
		Duration durationForEachField = duration.dividedBy(fieldLocators.size());
		for (By fieldLocator : fieldLocators) {
			waitUntilFieldIsFilled(fieldLocator, durationForEachField);
		}
	}

	public void waitUntilAttributeValueChange(By locator, String attribute, String initialValue, Duration duration) {
		WebDriverWait wait = new WebDriverWait(driver, duration);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String updatedAttributeValue = findElement(locator).getDomAttribute(attribute);
				return updatedAttributeValue != null && !updatedAttributeValue.equals(initialValue);
			}
		});
	}

	public void uploadFile(String filePath) throws AWTException {
		// Copy the file path to the clipboard
		StringSelection selection = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		Robot robot = new Robot();
		robot.delay(1000);

		// Simulate CTRL+V to paste the file path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// Press Enter to confirm the file upload
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void closePopUp(By locator) {
		if (isDisplayed(locator)) {
			click(locator);
		}
	}
	
	public void waitForDuration(Duration duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}