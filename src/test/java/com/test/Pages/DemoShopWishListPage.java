package com.test.Pages;

import com.google.inject.Inject;
import com.test.utils.ReadConfig;
import com.test.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DemoShopWishListPage extends ReadConfig {

    @Inject
    private SeleniumHelper SeleniumHelper;


    @FindBy(xpath="//h2[text()='My wishlist']")
    private WebElement pageHeaderWL;


    @FindBy(xpath="//a[@title='Cart']")
    private WebElement cartIcon;

    @FindBy(xpath="//a[contains(text(),'Add to cart')]")
    private WebElement addToCartBtn;

    @FindBy(xpath="//div[contains(text(),'Product added to cart successfully')]")
    private WebElement addedToCartMsg;

    @FindBy(xpath="//td[@class='product-remove']")
    private List<WebElement> removeFromCartBtns;

    @FindBy(xpath="//td[@class='product-price']")
    private List<WebElement> lsAllPrices;

    @FindBy(xpath="//td[@class='product-name']")
    private List<WebElement> lsAllName;

    @FindBy(xpath="//a[text()='Add to cart']")
    private List<WebElement> lsAddToCart;


    public DemoShopWishListPage(){
        PageFactory.initElements(driver,this);
    }

    static String strProdName;

    public void validateWLPage(){

        SeleniumHelper.isElementDisplayed(pageHeaderWL);

    }


    public void countNoOfItemInWishList(String arg0){
        //No of delete Icon is same as no of product in the cart

        Assert.assertEquals("No of Item in carts are : ",arg0,String.valueOf(removeFromCartBtns.size()) );

    }


    public void findLowestPriceProductFromWL(){


        LinkedList<String> al=new LinkedList<String>();


        for (int i = 0; i < lsAllPrices.size(); i++) {

            //if the price is already reduced then ignore the original price and consider reduced price
            if (lsAllPrices.get(i).getText().split(" ").length>1) {
                al.add(lsAllPrices.get(i).getText().split(" ")[1].replace("£",""));
            }
            else
            {
                al.add(lsAllPrices.get(i).getText().replace("£",""));
            }

        }

        //Use inbulit function to identify lowest value
        //System.out.println(Collections.min(al));
        // get the index of lowest value and click on add to cart link
        int indextoClick = al.indexOf (Collections.min(al));

        //get name of the product
        strProdName = lsAllName.get(indextoClick).getText();
        SeleniumHelper.click(lsAddToCart.get( indextoClick));


    }

    public void validateProdAddedToCartMsg(){

        SeleniumHelper.isElementDisplayed(addedToCartMsg);

    }



}
