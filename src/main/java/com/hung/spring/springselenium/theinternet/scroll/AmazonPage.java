package com.hung.spring.springselenium.theinternet.scroll;

import com.hung.spring.springselenium.kelvin.annotation.Page;
import com.hung.spring.springselenium.kelvin.service.CustomAction;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

@Page
public class AmazonPage extends Base {

    @Autowired
    private CustomAction customAction;

    @FindBy(id="nav-logo-sprites")
    private WebElement logo;

    @FindBy(xpath = "//h2[text()='New arrivals in Toys']")
    private WebElement topSeller;

    @FindBy(xpath = "//span[text()='© 1996-2022, Amazon.com, Inc. or its affiliates']")
    private WebElement copyright;


    @FindBy(id="twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id="nav-search-submit-button")
    private WebElement btnSubmit;


    public void goTo(){
        this.driver.get("https://www.amazon.com/");
    }

    public void searchProduct(String productName){
        customAction.type(this.searchBox, productName);
    }

    public void submit(){
        customAction.submit(this.btnSubmit);
    }

    public void scrollDown1000(){
        customAction.scrollByPixel(0,1000);
    }

    public void scrollToBottom(){
        customAction.scrollToBottom();
    }

    public void scrollToTopSeller(){
        customAction.scrollToElement(this.topSeller);
    }

    public boolean isTopSellerDisplay() {
        return this.wait.until((d) -> this.topSeller.isDisplayed());
    }

    public boolean isCopyRightDisplay() {
        return this.wait.until((d) -> this.copyright.isDisplayed());
    }

    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.logo.isDisplayed());
    }
}
