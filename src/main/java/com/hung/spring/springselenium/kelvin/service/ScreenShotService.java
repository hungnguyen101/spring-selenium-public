package com.hung.spring.springselenium.kelvin.service;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import com.hung.spring.springselenium.kelvin.annotation.TakeScreenShot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.nio.file.Path;

@LazyConfiguration
public class ScreenShotService {

    @Autowired
    private ApplicationContext ctx;
    //ChromeDriver extends from TakeScreenshot
    //Spring automatically convert ChromeDriver to TakeScreenshot

    @Value("${screenshot.path}")
    private Path path;

    public void takeScreenshot(String name) throws Exception {
        File sourceFile = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE); //call TakesScreenshot from Selenium
        System.out.println(path);
        FileCopyUtils.copy(sourceFile, this.path.resolve(name + ".jpg").toFile());
    }

    public byte[] getScreenShot(){
        return this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
    }

//    @PostConstruct
//    private void init(){
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Sleeping...");
//        }
//    }


}
