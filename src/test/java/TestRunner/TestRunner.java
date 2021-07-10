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
        tags="@Appium",
        plugin = {
                "pretty",
                "html:target/CucumberReport/cucumber.html",
                "json:target/CucumberReport/cucumber.json",
                "junit:target/CucumberReport/cucumber.xml",
               // "com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html",
                 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                //"com.cucumber.listener.ExtentCucumberFormatter:target/CucumberReport/report.html"
        }
)



public class TestRunner {


}


