package com.hung.spring.springselenium.windowswitch;

import com.google.common.util.concurrent.Uninterruptibles;
import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.kelvin.service.WindowSwitchService;
import com.hung.spring.springselenium.window.MainPage;
import com.hung.spring.springselenium.window.PageA;
import com.hung.spring.springselenium.window.PageB;
import com.hung.spring.springselenium.window.PageC;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WindowSwitchTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private MainPage mainPage;

    @LazyAutowired
    private PageA pageA;


    @LazyAutowired
    private PageB pageB;


    @LazyAutowired
    private PageC pageC;

    @LazyAutowired
    private WindowSwitchService switchService;

    @BeforeClass
    public void setUp(){
        this.mainPage.gotTo();
        this.mainPage.isReady();
        this.mainPage.launchAllWindows();
    }

    @Test
    public void switchTest(){
        this.switchService.switchByTitle("Page A");
        this.pageA.addToArea("Hello page A");

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        this.switchService.switchByIndex(2);
        this.pageB.addToArea("Hello page B");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        this.switchService.switchByTitle("Page C");
        this.pageC.addToArea("Hello page C");

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

    }

}
