package com.hung.spring.springselenium.kelvin.config;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@LazyConfiguration
public class WebDriverWaitConfig {

    @Value("${default.timeout: 30}")
    private int timeout;

    @Bean
    @Scope("prototype")
    public WebDriverWait webDriverWait(final WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
}
