package com.hung.spring.springselenium;

public class User {

    private Address address;
    private Salary salary;

    public User(Address address, Salary salary) {
        this.address = address;
        this.salary = salary;
    }

    public void printDetail(){
        System.out.println("Address :: " + this.address.getStreet());
        System.out.println("Salary :: " + this.salary.getAmount());

    }
}
