package com.hung.spring.springselenium.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

@Aspect
@Service
public class ExtentReportAspect {

    @After("@annotation(test) && within(com.hung.spring.springselenium..*)")
    public void afterTest(Test test){
        System.out.println("haha after test");
    }

    @After("@annotation(afterClass) && within(com.hung.spring.springselenium..*)")
    public void afterSuite(AfterClass afterClass){
        System.out.println("after suite");
    }
}
