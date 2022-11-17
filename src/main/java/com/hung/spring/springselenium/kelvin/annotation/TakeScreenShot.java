package com.hung.spring.springselenium.kelvin.annotation;

import java.lang.annotation.*;

@Page
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TakeScreenShot {
}
