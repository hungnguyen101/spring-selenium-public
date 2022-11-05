package com.hung.spring.springselenium.kelvin.service;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

@LazyConfiguration
public class CustomAction {

    private final WebDriver driver;

    private final Wait wait;

    public CustomAction(WebDriver driver, Wait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public void scrollToElement(WebElement elem){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", elem);
    }

    public void scrollToBottom(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollByPixel(long dx, long dy){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy("+dx+","+dy+")", "");
    }

    public void type(WebElement elem, String text) {
        this.wait.until((d) -> elem.isDisplayed());
        elem.clear();
        elem.sendKeys(text + "\n");
    }

    public void submit(WebElement elem) {
        this.wait.until((d) -> elem.isDisplayed());
        elem.submit();
    }
}
