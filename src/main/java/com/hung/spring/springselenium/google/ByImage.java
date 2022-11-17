package com.hung.spring.springselenium.google;

import com.hung.spring.springselenium.kelvin.annotation.PageFragment;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

@PageFragment
public class ByImage extends Base implements SearchStrategy {

    @FindBy(xpath = "//img[@alt='Camera search']")
    private WebElement cameraIcon;

    @FindBy(xpath = "//input[@placeholder='Paste image link']")
    private WebElement pasteLink;

    @FindBy(xpath = "//div[text()='Search']")
    private WebElement searchBtn;

    @Override
    public void search(Map<String, String> searchDetails) {
        this.wait.until(ExpectedConditions.visibilityOf(this.cameraIcon));
        this.cameraIcon.click();
        this.pasteLink.sendKeys(searchDetails.get("url"));
        this.searchBtn.click();
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
