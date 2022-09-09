package com.hung.spring.springselenium.kelvin.config;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import com.hung.spring.springselenium.kelvin.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

@LazyConfiguration
@Profile({"!grid & !browserstack"}) //local run, not import grid or browserstack profiles
public class WebDriverConfig {

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().browserVersion("104.0.5112.79").setup();
        return new ChromeDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver(){
        WebDriverManager.firefoxdriver().browserVersion("89.0").setup();
        return new FirefoxDriver();
    }

    @ThreadScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "safari")
    public WebDriver safariDriver(){
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }

}
