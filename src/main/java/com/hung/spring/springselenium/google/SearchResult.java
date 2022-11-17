package com.hung.spring.springselenium.google;

import com.hung.spring.springselenium.kelvin.annotation.PageFragment;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class SearchResult extends Base {

    @FindBy(css = "div.g")
    private List<WebElement> results;

    public int getResultCount(){
        return this.results.size();
    }

    @Override
    public boolean isReady() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }
}
