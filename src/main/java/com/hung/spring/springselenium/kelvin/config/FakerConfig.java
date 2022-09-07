package com.hung.spring.springselenium.kelvin.config;

import com.github.javafaker.Faker;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class FakerConfig {

    @Bean
    public Faker getConfig() {
        return new Faker();
    }
}
