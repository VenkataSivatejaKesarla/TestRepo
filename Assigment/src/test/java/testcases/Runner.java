package testcases;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(format = { "pretty", "json:target/Cucumber-report/cucumber.json" }, features = {
		"src/test/resources/Sample.feature" })

public class Runner {

	public void runTest() {

	}

}
