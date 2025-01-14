/*
package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


public class TestRun {
	
	

	
	//@RunWith(Cucumber.class)
	@CucumberOptions(
	    features = {"src/test/resources/Features"},
	    glue = {"stepDefinitions"},                
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    monochrome = true,                      
	    dryRun = false                          
	)
	public class TestRunner  extends AbstractTestNGCucumberTests {
	}

}
*/

package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    
    plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    features = {"src/test/resources/Features"},
		//features = {"src/test/resources/Features/A_Login.feature"},
		//features= {"src/test/resources/Features/SignUp.feature"},
    glue = {"stepDefinitions"},
    tags="@Regression",
   //plugin = {"pretty", "html:target/cucumber-reports/reports_html.html"},
    monochrome = true,
    dryRun = false
)

public class TestRun {}
