package pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import tsUtilities.SeleniumUtils;


//merging both the changes


public class HomePage {

	public static By txt_TypeLocation = By.xpath("//input[@placeholder='Type or Select Location']");

	public static By lst_SaleOrRent_SaleSelected = By.xpath("//button[@title='Sale']");

	public static String lst_RentOrSale_RentSelected = "//button[@title='~']";

	public static String lbl_RentOrSale = "//ul[@class='dropdown-menu']//a[text()='~']";

	// public static By lbl_Sale =
	// By.xpath("//ul[@class='dropdown-menu']//a[text()='Sale']");

	public static By lbl_AnPrice = By.xpath("//button[@title='Any Price']");

	public static By txt_MaxPrice = By.name("maxprice");

	public static By lbl_MoreOptions = By.xpath("//span[text()='More Options']");

	public static By chk_WithPhotos = By.name("with_photos");

	public static By lbl_FloorArea = By.xpath("//span[text()='Floor Area']");

	public static By txt_MaxSize = By.name("maxsize");

	public static By btn_Search = By.xpath("//input[@value='Search']");

	public static SeleniumUtils selenium;

	static {
		selenium = SeleniumUtils.getInstance();
	}

	public static void navigateToHomePage(String url) throws Throwable {

		selenium.openBrowser("chrome");

		selenium.getDriverInstance().get(url);

		selenium.waitForObject(HomePage.txt_TypeLocation);

		selenium.verifyObjectExists(HomePage.txt_TypeLocation);
		
		

	}

	public static void selectRentOrSale(String value) throws Throwable {

		lbl_RentOrSale = lbl_RentOrSale.replace("~", value);

		By lbl_RentOrSale1 = By.xpath(lbl_RentOrSale);

		selenium.waitForObject(HomePage.lst_SaleOrRent_SaleSelected);

		selenium.click(HomePage.lst_SaleOrRent_SaleSelected);

		selenium.click(lbl_RentOrSale1);

		lst_RentOrSale_RentSelected = lst_RentOrSale_RentSelected.replace("~", value);

		By lst_RentOrSale_RentSelected1 = By.xpath(lst_RentOrSale_RentSelected);

		Assert.assertTrue("Verify the section of Rent option",
				selenium.isElementDisplayed(lst_RentOrSale_RentSelected1));

	}

	public static void searchAndSelectLocation(String location, String valueToSelect) throws Throwable {

		selenium.SendKeys(HomePage.txt_TypeLocation, location);

		selenium.executeJavaScript(By.xpath("//p[@class='tt-sug-text' and contains(.,'" + valueToSelect + "')]"),
				"arguments[0].click();");

		//selenium.verifyObjectExists(By.xpath("//pre[contains(.,'" + valueToSelect + "')]"));
	}

	public static void setMaxAreaMoreOptionsAndClickSearch(String maxArea) throws Throwable {

		selenium.click(HomePage.lbl_MoreOptions);

		selenium.waitForObject(HomePage.lbl_FloorArea);

		selenium.click(HomePage.lbl_FloorArea);

		selenium.waitForObject(HomePage.txt_MaxSize);

		selenium.SendKeys(HomePage.txt_MaxSize, maxArea);

		selenium.click(HomePage.btn_Search);
	}

	public static void setMaxPrice(String MaxPrice) throws Throwable {

		selenium.click(HomePage.lbl_AnPrice);

		selenium.SendKeys(HomePage.txt_MaxPrice, MaxPrice);

		String MaxPrice1 = MaxPrice.replace(",", "");

		selenium.executeJavaScript(By.xpath("//li[@data-targetname='maxprice']//a[@href='#" + MaxPrice1 + "']"),
				"arguments[0].click();");

		Assert.assertTrue("Verify Selection of Max Price to" + MaxPrice,
				selenium.isElementDisplayed(By.xpath("//span[contains(text(),'" + MaxPrice + "')]")));

	}

	public static void setWithPhotosAndSearch() throws Throwable {

		selenium.click(HomePage.lbl_MoreOptions);

		Thread.sleep(5000);

		selenium.executeJavaScript(HomePage.chk_WithPhotos, "arguments[0].click();");

		selenium.click(HomePage.btn_Search);

	}

}
