package com.test.stepDefinitions;



import com.test.Pages.*;

import com.google.inject.Inject;
import com.test.utils.BrowserFactory;
import com.test.utils.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

import static com.test.utils.BrowserFactory.getBrowserDriver;


public class DemoShopStepDef extends ReadConfig {

    @Inject
    public DemoShopLandingPage DemoShopLandingPage;

    @Inject
    public DemoShopCartPage DemoShopCartPage;


    @Inject
    public DemoShopWishListPage DemoShopWishListPage;

    @Inject
    public BrowserFactory BrowserFactory;

    public static WebDriver driver;


    @Before
    public void setUp(){
        String browserName=prop.getProperty("browser");

        driver =  getBrowserDriver(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    @After
    public void tearDown(Scenario scn){

        driver.quit();
    }



    @AfterStep
    public void addScreenshot(Scenario scenario){
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");

        }

    }






    @When("I view my wishlist table")
    public void iViewMyWishlistTable() {

        DemoShopLandingPage.clickToWishListIcon();
    }



    @When("I search for lowest price product")
    public void iSearchForLowestPriceProduct() {
        DemoShopWishListPage.findLowestPriceProductFromWL();
    }

    @And("I am able to add the lowest price item to my cart")
    public void iAmAbleToAddTheLowestPriceItemToMyCart() {
        DemoShopWishListPage.validateProdAddedToCartMsg();
    }

    @Then("I am able to verify the item in my cart")
    public void iAmAbleToVerifyTheItemInMyCart() {
        DemoShopLandingPage.clickToCartIcon();
        //verify there only one item in cart
        DemoShopCartPage.countNoOfItemInCart("1");
        DemoShopCartPage.validateProdNameInCart();
    }





    @Given("I add {string} different products to my wish list")
    public void iAddDifferentProductsToMyWishList(String arg0) {

        //Add products to WishList
        DemoShopLandingPage.addProductToWishlist(arg0);


    }

    @Then("I find total {string} selected items in my Wishlist")
    public void iFindTotalSelectedItemsInMyWishlist(String arg0) {
        DemoShopWishListPage.validateWLPage();
        DemoShopWishListPage.countNoOfItemInWishList(arg0);
    }
}
