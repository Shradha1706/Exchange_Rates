package runner;

import java.io.File;


import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", 
                 glue = "com.test.Stepdefinition", 
                 plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" },
                 tags = {"@ExchangeRates"},
                 //plugin = {"com.vimalselvam.cucumber.ExtentCucumberFormatter:output/report.html"},
		         //plugin = { "pretty", "html:target/htmlreports" },
		         monochrome = true)

public class RunCucumberTest {
	@SuppressWarnings("unused")
	private TestNGCucumberRunner testNGCucumberRunner;

	
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File("src/test/resources/config/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows");
        Reporter.setTestRunnerOutput("Cucumber Test Runner");

	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@DataProvider
	public Object[][] features() {
		
		return testNGCucumberRunner.provideFeatures();
	}
	
	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		System.out.println("------------"+cucumberFeature.getCucumberFeature().getGherkinFeature().toString());
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		writeExtentReport();
		testNGCucumberRunner.finish();
	}

}