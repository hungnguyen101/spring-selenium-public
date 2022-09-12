package com.hung.spring.springselenium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features="classpath:features",//folder "features"
        glue = "com.hung.spring.springselenium.bdd", //step definition steps package
        tags = "@regression",
        plugin = {
                "pretty",
                "html:report/report.html" //report
        }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
