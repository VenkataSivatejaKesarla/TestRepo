package tsUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import testcases.Results;

public class SeleniumUtils {

	public static WebDriver driver;

	public static SeleniumUtils seleniumutil;

	public static String pathResources = "/src/test/resources";

	public static String pathDriver = pathResources + "/Drivers/";

	public static int waitTime = 120;

	public static SeleniumUtils getInstance() {
		if (seleniumutil == null) {
			seleniumutil = new SeleniumUtils();
		}

		return seleniumutil;
	}

	public WebDriver getDriverInstance() throws Throwable {
		return driver;
	}

	public void setDriverInstnace(WebDriver driver) throws Throwable {
		SeleniumUtils.driver = driver;
	}

	public boolean openBrowser(String BrowserType) throws Throwable {

		if (BrowserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + pathDriver + "chromedriver.exe");

			driver = new ChromeDriver();
			
			driver.manage().window().maximize();

		}

		return true;
	}

	public boolean isElementClickable(By objectName) throws Throwable {

		boolean objectClickable = false;

		try {

			WebDriverWait wait = new WebDriverWait(driver, 20);

			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(objectName));

			objectClickable = true;

		} catch (Exception e) {

			objectClickable = false;

		}

		System.out.println("element :" + objectName.toString() + "is clickable:" + objectClickable);

		return objectClickable;
	}

	public void click(By ObjectName) throws Throwable {
		if (IsElementVisible(ObjectName)) {
			WebDriverWait wait = new WebDriverWait(driver, 90);

			wait.until(ExpectedConditions.elementToBeClickable(ObjectName));

			driver.findElement(ObjectName).click();

			Thread.sleep(1000);
		}
	}

	public void quitApplication() throws Throwable {
		driver.quit();
	}

	public void SendKeys(By ObjectName, String value) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);

		wait.until(ExpectedConditions.presenceOfElementLocated(ObjectName));

		driver.findElement(ObjectName).sendKeys(value);

	}

	public void waitForObject(By ObjectName) throws Throwable {

		int count = 1;

		boolean elementFound = false;

		while (!elementFound) {

			elementFound = isElementPresent(ObjectName);

			if (elementFound) {

				break;
			} else if (count == waitTime) {

				System.out.println("not able to find the object :" + ObjectName + "in " + waitTime);
			}

		}

	}

	public boolean isElementDisplayed(By ObjectName) {

		WebDriverWait wait = new WebDriverWait(driver, 120);

		wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectName));

		WebElement WE = driver.findElement(ObjectName);

		System.out.println("element :" + ObjectName + "is" + WE.isDisplayed());

		return WE.isDisplayed();

	}

	public void insertScreenshot(Scenario scenario) throws Throwable {

		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		scenario.embed(screenshot, "image/png");

	}

	public void checkBoxSelection(String chkBoxStatus, By ObjectName) throws Throwable {

		boolean isSelected;

		isSelected = driver.findElement(ObjectName).isSelected();

		String ischkBoxSelected = "";

		if (isSelected) {
			ischkBoxSelected = "on";
		} else {
			ischkBoxSelected = "off";

		}

		if (!chkBoxStatus.toLowerCase().equals(ischkBoxSelected)) {

			driver.findElement(ObjectName).click();
		}
	}

	public boolean isAttributePresent(WebElement ele, String attribute) throws Throwable {

		Boolean result = false;

		try {
			String attrpresent = ele.getAttribute(attribute);

			if (attrpresent != null) {

				result = true;

			}

		} catch (Exception e) {

		}

		return result;
	}

	public void executeJavaScript(By ObjectName, String JavaScriptFunction) throws Throwable {

		Thread.sleep(4000);

		WebElement elementOnJS = driver.findElement(ObjectName);

		((JavascriptExecutor) driver).executeScript(JavaScriptFunction, elementOnJS);
	}

	public void verifyObjectExists(By ObjectName) throws Throwable {

		boolean bsuccess = isElementPresent(ObjectName);

		if (bsuccess) {

			Results.reportEvent("Dispaly of element" + ObjectName.toString(), "PASS");

			org.junit.Assert.assertFalse("Verification of Existence of object" + bsuccess, false);
		} else {

			Results.reportEvent("Dispaly of element" + ObjectName.toString(), "FAIL");

			org.junit.Assert.assertFalse("Verification of Existence of object" + bsuccess, true);

		}

	}

	public boolean isElementPresent(By ObjectName) throws Throwable {

		int count = 0;

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);

			wait.until(ExpectedConditions.visibilityOfElementLocated(ObjectName));
		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		try {
			count = driver.findElements(ObjectName).size();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (count == 0) {
			return false;
		} else {
			return true;
		}

	}

	public void clearTextField(By ObjectName) throws Throwable {

		try {
			driver.findElement(ObjectName).clear();

		} catch (Exception e) {
			driver.findElement(ObjectName).sendKeys("");
		}
	}

	public boolean IsElementVisible(By ObjectName) throws Throwable {

		boolean ObjectVisible = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);

			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(ObjectName));

			ObjectVisible = element.isDisplayed();
		} catch (Exception e) {
			ObjectVisible = false;
		}

		return ObjectVisible;
	}

}
