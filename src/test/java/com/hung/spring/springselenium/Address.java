package com.hung.spring.springselenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Address {

    private String street;

    public Address() {
        this.street = "123 dao duy anh";
    }

    public String getStreet() {
        return street;
    }
}
