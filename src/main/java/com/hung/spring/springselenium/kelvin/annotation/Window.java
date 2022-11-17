package com.hung.spring.springselenium.kelvin.annotation;

import java.lang.annotation.*;

@Page
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Window {
    String value() default ""; //default value is empty
}
