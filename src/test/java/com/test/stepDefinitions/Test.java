package com.test.stepDefinitions;



import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.annotations.*;


public class Test {
    AndroidDriver driver;



    @Given("Test calculator")
    public void setUp() throws MalformedURLException, InterruptedException {
        //Set up desired capabilities and pass the Android app-activity and app-package to Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("BROWSER_NAME", "Android");
        capabilities.setCapability("VERSION", "9");
        capabilities.setCapability("deviceName","Vivek");
        capabilities.setCapability("platformName","Android");


        capabilities.setCapability("appPackage", "com.miui.calculator");
// This package name of your app (you can get it from apk info app)
        capabilities.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity"); // This is Launcher activity of your app (you can get it from apk info app)
//Create RemoteWebDriver instance and connect to the Appium server
        //It will launch the Calculator App in Android Device using the configurations specified in Desired Capabilities
        driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);



        //------------------------------------------------

        Thread.sleep(10000);
        //locate the Text on the calculator by using By.name()
        WebElement two=driver.findElement(By.id("com.miui.calculator:id/btn_2_s"));
        two.click();
        WebElement plus=driver.findElement(By.id("com.miui.calculator:id/btn_plus_s"));
        plus.click();
        WebElement four=driver.findElement(By.id("com.miui.calculator:id/btn_4_s"));
        four.click();
        WebElement equalTo=driver.findElement(By.id("com.miui.calculator:id/btn_equal_s"));
        equalTo.click();
        //locate the edit box of the calculator by using By.tagName()
        WebElement results=driver.findElement(By.id("com.miui.calculator:id/result"));
        //Check the calculated value on the edit box
        assert results.getText().equals("= 6"):"Actual value is : "+results.getText()+" did not match with expected value: 6";


        //----------------------------------------------------
        //close the app
        driver.quit();


    }

}
