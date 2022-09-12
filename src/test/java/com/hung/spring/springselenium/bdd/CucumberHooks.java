package com.hung.spring.springselenium.bdd;

import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.kelvin.service.ScreenShotService;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

public class CucumberHooks {

    @LazyAutowired
    private ScreenShotService screenshotService;

    @LazyAutowired
    private ApplicationContext ctx;

    @AfterStep
    public void takeScreenShotAfterFailedTest(Scenario scenario){
        if(scenario.isFailed()){
            scenario.attach(this.screenshotService.getScreenShot(), "image/png", scenario.getName());
        }
    }

    @After
    public void afterScenario(){
        this.ctx.getBean(WebDriver.class).quit();
    }
}
