package com.hung.spring.springselenium.aop;

import com.hung.spring.springselenium.kelvin.annotation.TakeScreenShot;
import com.hung.spring.springselenium.kelvin.service.ScreenShotService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ScreenShotAspect {

    @Autowired
    private ScreenShotService screenShotService;

    @After("@annotation(takeScreenshot)")
    public void after(TakeScreenShot takeScreenshot) throws Exception {
        this.screenShotService.takeScreenshot("screenshotName");
    }
}
