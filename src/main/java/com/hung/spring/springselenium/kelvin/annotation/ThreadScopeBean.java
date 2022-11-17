package com.hung.spring.springselenium.kelvin.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

@Bean
@Scope("browserscope") //read BrowserScopeConfig to understand this custom scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ThreadScopeBean {
}
