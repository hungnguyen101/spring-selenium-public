package com.hung.spring.springselenium.googletest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.google.GooglePage;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.kelvin.service.ScreenShotService;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Google2Test extends SpringBaseTestNGTest {

    @LazyAutowired
    private GooglePage googlePage;

    @Test
    public void googleTest() throws Exception {

        this.googlePage.goTo();
        Assert.assertTrue(this.googlePage.isReady());

        this.googlePage.getSearchComponent().search("selenium");
        Assert.assertTrue(this.googlePage.getSearchResult().getResultCount() > 2);

        this.googlePage.close();
    }
}
