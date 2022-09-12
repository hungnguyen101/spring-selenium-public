package com.hung.spring.springselenium.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public class ExtentReport {

    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();

        ExtentReports extentReports = new ExtentReports();
        File file = new File("report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);

        //change view order
        sparkReporter.viewConfigurer().viewOrder().as(new ViewName[]{
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.LOG,
                ViewName.EXCEPTION,
                ViewName.CATEGORY,
                ViewName.DEVICE,
        }).apply();


        sparkReporter.loadJSONConfig(new File ("./src/test/resources/extentReport/extent-report-config.json"));

        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Browser", capabilities.getBrowserName() +  " " + capabilities.getBrowserVersion());
        extentReports.setSystemInfo("app url", driver.getCurrentUrl());

        extentReports.attachReporter(sparkReporter);

        extentReports.createTest("Test 1", "haha")
                .assignAuthor("HungQA")
                .assignCategory("smoke")
                .assignDevice("Firefox66")
                .pass("This is a test passed");

        extentReports.createTest("Test 2", "haha")
                .assignAuthor("HungQA")
                .assignCategory("smoke")
                .assignDevice("Firefox 99")
                .skip("This is a skipped test");

        extentReports.createTest("Test 1", "haha")
                .assignAuthor("HungQA")
                .assignCategory("regression")
                .assignDevice("Firefox66")
                .fail("This is a test failed");

        extentReports.createTest("Test 3", "haha")
                .assignAuthor("HungQA")
                .assignCategory(new String[]{"smoke", "regression"})
                .assignDevice("Firefox66")
                .fail("This is a test failed");

        extentReports.createTest("Test 4", "haha")
                .assignAuthor("HungQA")
                .assignCategory("regression")
                .assignDevice("Firefox66")
                .pass("This is a test passed");



        System.getProperties().list(System.out);
        extentReports.flush();

        driver.close();


    }
}
