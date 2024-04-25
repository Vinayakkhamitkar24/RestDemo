package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
	features = "src/test/java/features",
	plugin={"json:target/jsonReports/cucumber-reports.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
	glue= {"stepDefinations"},
	monochrome=true
    //tags="@DeletePlace"
		)
public class TestRunner {

}
