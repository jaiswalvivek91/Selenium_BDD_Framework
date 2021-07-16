package com.test.Pages;

import com.google.inject.Inject;
import com.test.utils.ReadConfig;
import com.test.utils.SeleniumHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DemoShopLandingPage extends ReadConfig {

    @Inject
    private SeleniumHelper SeleniumHelper;


    @FindBy(className="custom-logo-link")
    private WebElement demoShoplogo;




    @FindBy(xpath="//a[@title='Cart']")
    private WebElement cartIcon;

    @FindBy(xpath="//a[@title='Wishlist']")
    private WebElement wishListIcon;

    @FindBy(xpath="//a[contains(text(),'Add to cart')]")
    private WebElement addToCartBtn;


    @FindBy(xpath="//a[contains(text(),'Add to cart')]")
    private List<WebElement> addTocartBtns;

    //@FindBy(xpath="//li[./a[contains(text(),'Add to cart')]]//span[text()='Add to wishlist']")
    @FindBy(xpath="//li[./a[contains(text(),'Add to cart') or contains(text(),'Buy now!') or contains(text(),'View products')]]//span[text()='Add to wishlist']")
    private List<WebElement> addToWLBtns;


    public DemoShopLandingPage(){
        PageFactory.initElements(driver,this);
    }




    public void addProductToCart(String arg0){

        for (int i = 0; i < Integer.parseInt(arg0); i++) {
            System.out.println(i);
            SeleniumHelper.click(addTocartBtns.get(i));
           // addTocartBtns.get(i).click();
        }



    }

    public void addProductToWishlist(String arg0){

        for (int i = 0; i < Integer.parseInt(arg0); i++) {
            System.out.println(i);

            SeleniumHelper.click(addToWLBtns.get(i));

        }



    }

    public void clickToCartIcon(){

       SeleniumHelper.click(cartIcon);

    }


    public void clickToWishListIcon(){

        SeleniumHelper.click(wishListIcon);

    }


}
