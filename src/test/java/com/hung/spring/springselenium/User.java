package com.hung.spring.springselenium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    private Salary salary;

    private Address address;

    @Autowired
    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }

    public void printDetails(){
        System.out.println("Address :: " + address.getStreet());
        System.out.println("Salary ::" + salary.getAmount());
    }


}
