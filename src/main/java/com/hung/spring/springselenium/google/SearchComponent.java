package com.hung.spring.springselenium.google;

import com.hung.spring.springselenium.kelvin.annotation.PageFragment;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

@PageFragment
public class SearchComponent extends Base {

    @FindBy(name = "q")
    private WebElement searchBox;

    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy){
        this.searchStrategy = searchStrategy;
        System.out.println(this.searchStrategy);
        PageFactory.initElements(driver, this.searchStrategy);
        System.out.println(this.searchStrategy);

    }

    public void search(Map<String, String> searchDetails){
        System.out.println(this.searchStrategy);
        this.searchStrategy.search(searchDetails);
    }

    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }




}
