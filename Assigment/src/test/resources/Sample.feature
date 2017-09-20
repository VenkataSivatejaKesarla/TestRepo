Feature: To test my cucumber test is running
  I want to run a sample feature file.

  Scenario: Search By A Property
    Given I Navigate to "http://propertyguru.com.sg"
    When I Search for "Marina" and Select "Marina Bay Residences"
    And I click on Search with maximum floor area "3000"
    Then I should see all listings displayed are with in search criteria

  Scenario: Verify	the	image	displayed	on	listing	details	page
    Given I Navigate to "http://propertyguru.com.sg"
    When I Select the Listing Type as "Rent"
    And I Select the maximum price as "15,000"
    And I click on Search with photos option
    Then All the images in first listing should display properly
