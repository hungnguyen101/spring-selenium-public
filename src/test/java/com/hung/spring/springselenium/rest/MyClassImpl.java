package com.hung.spring.springselenium.rest;

public class MyClassImpl implements MyInterface{

    MyInterface myInterface;

    public MyInterface printMe(){
        System.out.println("I'm me");
        return this.myInterface;
    }
}
