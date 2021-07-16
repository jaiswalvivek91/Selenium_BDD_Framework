package com.test.utils;

//import enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory extends ReadConfig {

	public static WebDriver getBrowserDriver(String strBrowserName){


		switch (strBrowserName.toUpperCase().trim()) {
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
				driver= new ChromeDriver();
				log.info("chrome browser opened");
				return driver;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver= new FirefoxDriver();
                log.info("firefox browser opened");
				return driver;
			case "IE":
				return new InternetExplorerDriver();
			default:
				throw new WebDriverException("No browser specified");
		}
	}


}
