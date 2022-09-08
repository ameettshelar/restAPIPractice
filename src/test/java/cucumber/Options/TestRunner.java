package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", glue= {"stepDefinations"},
				monochrome = true,
				plugin = {"pretty","json:target/cucumber-reports/reports.json",
						  "json:target/cucumber-reports/cucumber.runtime.formatter.JSONFormatter"})

				
public class TestRunner {
//, tags= "@DeletePlace"
}
