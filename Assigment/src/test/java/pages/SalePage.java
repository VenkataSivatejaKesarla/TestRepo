package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testcases.Results;
import tsUtilities.SeleniumUtils;

public class SalePage {

	public static SeleniumUtils selenium;

	public static By tbl_SearchResults = By.xpath("//ul[@class='listing-items']");

	public static By tbl_PropertyNames = By.xpath("//ul[@class='listing-items']//span[@itemprop='name']");

	public static By tbl_PropertySize = By.xpath("//ul[@class='listing-items']//li[@class='lst-sizes']");

	public static By lbl_PaginationNext = By.xpath("//li[@class='pagination-next']/a[1]");

	static {

		selenium = SeleniumUtils.getInstance();
	}

	public static void verifyDisplayOfSearchResults() throws Throwable {

		selenium.waitForObject(SalePage.tbl_SearchResults);

		selenium.verifyObjectExists(tbl_SearchResults);
	}

	public static void verifySearchResultsMatchSearchCriteria() throws Throwable {

		boolean firstpage = true;

		int i = 0;

		while (!(selenium.getDriverInstance().findElements(SalePage.lbl_PaginationNext).isEmpty())) {

			if (!(firstpage)) {

				selenium.executeJavaScript(SalePage.lbl_PaginationNext, "arguments[0].click();");

			}

			selenium.waitForObject(SalePage.tbl_SearchResults);

			List<WebElement> resultList = selenium.getDriverInstance().findElements(SalePage.tbl_PropertyNames);

			List<WebElement> resultListSize = selenium.getDriverInstance().findElements(SalePage.tbl_PropertySize);

			i = i + 1;

			boolean result = verifySearchResults(resultList);

			Assert.assertTrue("Verify all the search results are from :Marina Bay Residences", result);

			if (result) {

				Results.reportEvent("Page No:" + i + "Verify if the search results are from :Marina Bay Residences",
						"PASS");

			} else {

				Results.reportEvent("Page No:" + i + "Verify if the search results are from:Marina Bay Residences",
						"FAIL");

			}

			result = verifySearchResultsSize(resultListSize);
			Assert.assertTrue("Verify all the search results are with in :3000Sqft", result);

			if (result) {

				Results.reportEvent("Page No:" + i + "Verify all the search results are with in :3000Sqft", "PASS");

			} else {
				Results.reportEvent("Page No:" + i + "Verify all the search results are with in :3000Sqft", "FAIL");
			}

			firstpage = false;

		}

	}

	public static boolean verifySearchResults(List<WebElement> lst) {

		for (WebElement ele : lst) {

			if (!(ele.getText().trim().equalsIgnoreCase("Marina Bay Residences"))) {

				return false;
			}

		}

		return true;
	}

	public static boolean verifySearchResultsSize(List<WebElement> lst) {

		int sqft;

		for (WebElement ele : lst) {

			sqft = Integer.parseInt(ele.getText().split("sqft")[0].replace(" ", ""));

			if (sqft > 3000) {

				return false;
			}

		}

		return true;
	}

}
