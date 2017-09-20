package testcases;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import tsUtilities.SeleniumUtils;

public class Results {

	public static Scenario scenario;

	SeleniumUtils selenium;

	public Results() {

		selenium = SeleniumUtils.getInstance();
	}

	public static void reportEvent(String StepDescription, String StepStatus) {

		if (StepStatus.equalsIgnoreCase("pass")) {
			scenario.write("<span style='float: left;'>" + StepDescription
					+ "</span><span style='float: right;color:green;font-weight:bold;'>Pass</span><br>");
		} else {
			scenario.write("<span style='float: left;'>" + StepDescription
					+ "</span><span style='float: right;color:red;font-weight:bold;'>Pass</span><br>");
		}

	}

	@Before
	public void before(Scenario scenario) {
		Results.scenario = scenario;
	}

	@After
	public void embedScreenshot(Scenario scenario) throws Throwable {

		if (scenario.isFailed()) {
			selenium.insertScreenshot(scenario);
		}

	}

}
