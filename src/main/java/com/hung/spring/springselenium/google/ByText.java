package com.hung.spring.springselenium.google;

import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class ByText extends Base implements SearchStrategy {

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;

    @Override
    public void search(Map<String, String> searchDetails) {
        this.searchBox.sendKeys(searchDetails.get("keyword"));
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns.stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
