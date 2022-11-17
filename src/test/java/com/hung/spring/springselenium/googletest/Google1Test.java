package com.hung.spring.springselenium.googletest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.google.ByImage;
import com.hung.spring.springselenium.google.ByText;
import com.hung.spring.springselenium.google.GooglePage;
import com.hung.spring.springselenium.google.SearchStrategy;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Google1Test extends SpringBaseTestNGTest {

    @LazyAutowired
    private GooglePage googlePage;

    @Test(dataProvider = "searchData")
    public void googleTest(SearchStrategy searchStrategy, Map<String, String> searchDetails) throws Exception {

        this.googlePage.goTo();
        Assert.assertTrue(this.googlePage.isReady());

        this.googlePage.getSearchComponent().setSearchStrategy(searchStrategy);

        this.googlePage.getSearchComponent().search(searchDetails);

        this.googlePage.close();
    }

    @DataProvider(name = "searchData")
    public Object[][] getData() {
        Map<String, String> text = new HashMap<>();
        text.put("keyword", "Hello google");

        Map<String, String> image = new HashMap<>();
        text.put("url", "https://images.ctfassets.net/lzny33ho1g45/T5qqQQVznbZaNyxmHybDT/b76e0ff25a495e00647fa9fa6193a3c2/best-url-shorteners-00-hero.png");

        return new Object[][]{
                {new ByText(), text},
                {new ByImage(), image}
        };
    }
}
