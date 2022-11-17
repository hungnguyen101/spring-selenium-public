package com.hung.spring.springselenium.kelvin.service;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

@LazyConfiguration
public class CustomAction {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final JavascriptExecutor js;
    private final Actions action;

    public CustomAction(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        js = (JavascriptExecutor)driver;
        this.wait = wait;
        action = new Actions(this.driver);
    }


    public void scrollToElement(WebElement elem){
        js.executeScript("arguments[0].scrollIntoView();", elem);
    }

    public void scrollToBottom(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollByPixel(long dx, long dy){
        js.executeScript("window.scrollBy("+dx+","+dy+")", "");
    }

    public void type(WebElement elem, String text) {
        this.wait.until((d) -> elem.isDisplayed());
        elem.clear();
        elem.sendKeys(text + "\n");
    }

    public void click(WebElement elem){
        this.wait.until((d) -> elem.isDisplayed());
        elem.click();
    }

    public void submit(WebElement elem) {
        this.wait.until((d) -> elem.isDisplayed());
        elem.submit();
    }

    public void rightClick(WebElement elem) {
        action.contextClick(elem).perform();
    }

    public void doubleClick(WebElement elem) {
        action.doubleClick(elem).perform();
    }

    //move mouse over a menu then select
    public void moveOverAndDoubleClick(WebElement elem, WebElement target) {
        action.moveToElement(elem).doubleClick(target).build().perform();
    }

    public void dragAndDrop(WebElement source, WebElement target){
        action.moveToElement(source).dragAndDrop(source, target).build().perform();
    }

    //Switch to the alert box and click on OK button
    public void switchToAlert(){
        Alert alert = this.driver.switchTo().alert();
        System.out.println("Alert Text\n" +alert.getText());
        alert.accept();
    }

    public boolean compareText(WebElement elem, String text) {
        this.wait.until((d) -> elem.isDisplayed());
        return elem.getText().equals(text);
    }
}
