package com.hung.spring.springselenium.kelvin.config;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import com.hung.spring.springselenium.kelvin.annotation.ThreadScopeBean;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@LazyConfiguration
@Profile("browserstack")
public class BrowserStackConfig {

    @Value("${browserstack.url}")
    private URL url;

    @ThreadScopeBean
    @ConditionalOnProperty(name="browser", havingValue="firefox")
    public WebDriver remoteFirefoxDriver() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browserVersion", "15.0");
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "OS X");
        browserstackOptions.put("osVersion", "Monterey");
        browserstackOptions.put("local", "false");
        capabilities.setCapability("bstack:options", browserstackOptions);
        return new RemoteWebDriver(this.url, capabilities);
    }

    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver remoteChromeDriver() throws MalformedURLException {
        // Add the following capabilities to your test script
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "10");

        capabilities.setCapability("bstack:options", browserstackOptions);
        return new RemoteWebDriver(this.url, capabilities);
    }
}
