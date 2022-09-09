package com.hung.spring.springselenium;

import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@SpringBootTest
public class SpringBaseTestNGTest extends AbstractTestNGSpringContextTests {

    @LazyAutowired
    private WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        this.driver.manage().window().maximize();
    }

    @AfterClass
    public void quitDriver(){
        this.driver.quit();
    }

}
