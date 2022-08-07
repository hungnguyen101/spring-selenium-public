package com.hung.spring.springselenium.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;

@Lazy
@Component
public class ScreenShotUtil {

    @Autowired
    private TakesScreenshot driver;
    //ChromeDriver extends from TakeScreenshot
    //Spring automatically convert ChromeDriver to TakeScreenshot

    @Value("${screenshot.path}")
    private Path path;

    public void takeScreenshot(String imageName) throws Exception {
        File sourceFile = this.driver.getScreenshotAs(OutputType.FILE);
        System.out.println(path);
        FileCopyUtils.copy(sourceFile, this.path.resolve(imageName).toFile());
    }

    @PostConstruct
    private void init(){
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sleeping...");
        }
    }


}
