package com.hung.spring.springselenium.kelvin.service;

import com.github.javafaker.Faker;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.nio.file.Path;

@Lazy
@Service
public class ScreenShotService {

    @Autowired
    private ApplicationContext ctx;
    //ChromeDriver extends from TakeScreenshot
    //Spring automatically convert ChromeDriver to TakeScreenshot

    @Value("${screenshot.path}")
    private Path path;

    @Autowired
    private Faker faker;

    public void takeScreenshot() throws Exception {
        File sourceFile = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE); //call TakesScreenshot from Selenium
        System.out.println(path);
        FileCopyUtils.copy(sourceFile, this.path.resolve(faker.name().firstName() + ".png").toFile());
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
