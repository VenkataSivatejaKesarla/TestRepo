package pages;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testcases.Results;
import tsUtilities.SeleniumUtils;

public class RentPage {

	public static SeleniumUtils selenium;

	public static By tbl_SearchResults = By.xpath("//ul[@class='listing-items']");

	public static By lnk_FirstSearchResultImageSet = By.xpath("(//a[@class='listing-img-a'])[1]");

	public static By img_ImagesInFirstSearchResult = By.xpath("//span[@class='gallery-item image']//img");

	public static By lbl_angleRight = By.xpath("//span[@class='pgicon pgicon-angle-right']");

	public static By img_ActiveImageUnderDisplay = By
			.xpath("//div[contains(@class,'img') and contains(@class,'active')]//img");

	static {

		selenium = SeleniumUtils.getInstance();

	}

	public static void verifyDisplayOfSearchResults() throws Throwable {

		selenium.waitForObject(SalePage.tbl_SearchResults);

		selenium.verifyObjectExists(tbl_SearchResults);
	}

	public static void verifySearchResultsAreDipslayingProperImages() throws Throwable {

		selenium.waitForObject(RentPage.lnk_FirstSearchResultImageSet);

		selenium.executeJavaScript(RentPage.lnk_FirstSearchResultImageSet, "arguments[0].click();");

		// selenium.click(RentPage.lnk_FirstSearchResultImageSet);

		Thread.sleep(4000);

		List<WebElement> imagesList = selenium.getDriverInstance().findElements(RentPage.img_ImagesInFirstSearchResult);

		System.out.println("Total no. of images are " + imagesList.size());

		for (int i = 0; i < imagesList.size(); i++) {

			Thread.sleep(2000);

			selenium.isElementClickable(RentPage.lbl_angleRight);

			selenium.click(RentPage.lbl_angleRight);

			selenium.IsElementVisible(RentPage.img_ActiveImageUnderDisplay);

			if (i == (imagesList.size() - 1)) {

				if (selenium.getDriverInstance().findElements(RentPage.img_ActiveImageUnderDisplay).size() == 0) {
					break;
				}

			}

			WebElement ele = selenium.getDriverInstance().findElement(RentPage.img_ActiveImageUnderDisplay);

			Assert.assertTrue("Verify Image Number:" + i + 1, verifyimageActive(ele));

			System.out.println(i);

		}

	}

	public static boolean verifyimageActive(WebElement imgElement) {

		boolean isValidImage = true;
		try {

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200) {
				isValidImage = false;
			} else {
				// System.out.println("valid image");
				Results.reportEvent("Image validation is successful for" + imgElement.getAttribute("src"), "PASS");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isValidImage;
	}

}
