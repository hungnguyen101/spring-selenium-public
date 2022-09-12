package com.hung.spring.springselenium;

import com.aventstack.extentreports.ExtentReports;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.kelvin.service.ReportListener;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@SpringBootTest
@Listeners({ReportListener.class})
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
