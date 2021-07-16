package com.test.Pages;

import com.google.inject.Inject;
import com.test.utils.ReadConfig;
import com.test.utils.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DemoShopCartPage extends ReadConfig {

    @Inject
    private SeleniumHelper SeleniumHelper;



    @FindBy(xpath="//h1[@class='single-title' and text()='Cart']")
    private WebElement pageHeaderCart;

    @FindBy(xpath="//td[@class='product-remove']")
    private List<WebElement> addTocartBtns;

    @FindBy(xpath="//td[@class='product-name']")
    private WebElement addedProd;

    public DemoShopCartPage(){
        PageFactory.initElements(driver,this);
    }


    public void validateCartPage(){
        SeleniumHelper.isElementDisplayed(pageHeaderCart);
    }

    public void countNoOfItemInCart(String arg0){
        //No of delete Icon is same as no of product in the cart

        Assert.assertEquals("No of Item in carts are : ",arg0,String.valueOf(addTocartBtns.size()) );


    }


    public void validateProdNameInCart(){
        SeleniumHelper.isElementDisplayed(addedProd);
        Assert.assertEquals("Verify the name of the prod : ",DemoShopWishListPage.strProdName,addedProd.getText() );

    }


}
