package com.hung.spring.springselenium.aop;

import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.kelvin.annotation.Window;
import com.hung.spring.springselenium.kelvin.service.WindowSwitchService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class WindowAspect {

    @LazyAutowired
    private WindowSwitchService switchService;


    /*
    * Use @target instead of here
    * because window @Window contains @Page annotation for a class object
    * @annotation - limits matching to join points where the subject of the join point (method being executed in Spring AOP) has the given annotation
     * */

    @Before("@target(window) && within(com.hung.spring.springselenium..*)")
    public void before(Window window)
    {
        this.switchService.switchByTitle(window.value());
    }

    @After("@target(window) && within(com.hung.spring.springselenium..*)")
    public void after(Window window)
    {
        this.switchService.switchByIndex(0);
    }

}
