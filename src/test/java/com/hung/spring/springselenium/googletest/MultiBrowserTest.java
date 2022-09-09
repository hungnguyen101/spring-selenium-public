package com.hung.spring.springselenium.googletest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

public class MultiBrowserTest extends SpringBaseTestNGTest {
    //in case you want to have more control on bean
    //turn off @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    //to run this test

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void test(){
        //we never use this way, better to user Driver Factory
        this.ctx.getBean("chromeDriver", WebDriver.class).get("https://www.google.com");
        this.ctx.getBean("firefoxDriver", WebDriver.class).get("https://www.facebook.com");

    }
}
