package com.hung.spring.springselenium.kelvin.service;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

@Lazy
@Service
public class ReportListener implements ITestListener {

    private ExtentReports extentReports;

    private ExtentSparkReporter sparkReporter;

    public static ExtentTest extentTest;

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private ScreenShotService screenShotService;

    @Override
    public void onTestStart(ITestResult result) {
//        WebDriver driver = this.ctx.getBean(WebDriver.class);
//        System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserName());
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = this.ctx.getBean(WebDriver.class);
//        System.out.println(((RemoteWebDriver) driver).getCapabilities().getBrowserName());
//        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
//        String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
        extentTest = extentReports.createTest(result.getMethod().getMethodName(), "")
                .assignAuthor("HungQA")
                .assignCategory("regression")
                .pass("This is a test passed");
        screenShotService.takeScreenshot(result.getMethod().getId());
        extentTest.addScreenCaptureFromPath("./screenshot/" + result.getMethod().getId() + ".jpg");

    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        extentReports.createTest(result.getMethod().getMethodName(), "")
                .assignAuthor("HungQA")
                .assignCategory("regression")
                .assignDevice("Firefox67")
                .fail("This is a test passed");
        screenShotService.takeScreenshot(result.getMethod().getId());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }




    @SneakyThrows
    @Override
    public void onStart(ITestContext context) {
        this.extentReports = new ExtentReports();
        File file = new File("report.html");
        this.sparkReporter = new ExtentSparkReporter(file);

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

        extentReports.attachReporter(sparkReporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        this.extentReports.flush();
    }
}
