package testcases;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.RentPage;
import pages.SalePage;
import tsUtilities.SeleniumUtils;

public class StepDefintions {

	public SeleniumUtils selenium;

	public StepDefintions() {

		selenium = SeleniumUtils.getInstance();

	}

	@Given("^I Navigate to \"([^\"]*)\"$")
	public void i_Navigate_to(String URL) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		HomePage.navigateToHomePage(URL);
	}

	@When("^I Search for \"([^\"]*)\" and Select \"([^\"]*)\"$")
	public void i_Search_for_and_Select(String location, String valueToSelect) throws Throwable {

		HomePage.searchAndSelectLocation(location, valueToSelect);

	}

	@And("^I click on Search with maximum floor area \"([^\"]*)\"$")
	public void i_click_on_Search_with_maximum_floor_area(String maxArea) throws Throwable {

		HomePage.setMaxAreaMoreOptionsAndClickSearch(maxArea);

		SalePage.verifyDisplayOfSearchResults();

	}

	@Then("^I should see all listings displayed are with in search criteria$")
	public void i_should_see_all_listings_displayed_are_with_in_search_criteria() throws Throwable {

		SalePage.verifySearchResultsMatchSearchCriteria();

		selenium.quitApplication();
	}

	@When("^I Select the Listing Type as \"([^\"]*)\"$")
	public void i_Select_the_Listing_Type_as(String value) throws Throwable {

		HomePage.selectRentOrSale(value);
	}

	@And("^I Select the maximum price as \"([^\"]*)\"$")
	public void i_Select_the_maximum_price_as(String MaxPrice) throws Throwable {

		HomePage.setMaxPrice(MaxPrice);
	}

	@And("^I click on Search with photos option$")
	public void i_click_on_Search_with_photos_option() throws Throwable {

		HomePage.setWithPhotosAndSearch();

		RentPage.verifyDisplayOfSearchResults();
	}

	@Then("^All the images in first listing should display properly$")
	public void all_the_images_in_first_listing_should_display_properly() throws Throwable {

		RentPage.verifySearchResultsAreDipslayingProperImages();

		selenium.quitApplication();

	}

}