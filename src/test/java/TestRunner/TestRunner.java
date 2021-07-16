package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.Properties;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
        ,glue={"com/test/stepDefinitions"  },
        monochrome = true,
        //junit = "--step-notifications",
        tags="@DemoShopTest",
        plugin = {
                "pretty",
                "html:target/CucumberReport/cucumber.html",
                "json:target/CucumberReport/cucumber.json",
                "junit:target/CucumberReport/cucumber.xml",

                 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",

        }
)



public class TestRunner {


}


