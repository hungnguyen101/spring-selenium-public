package com.hung.spring.springselenium.kelvin.scope;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

public class BrowserScope extends SimpleThreadScope {
    //we wil override get method

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object o = super.get(name, objectFactory);
        SessionId sessionID = ((RemoteWebDriver)o).getSessionId();
        if(Objects.isNull(sessionID)){//when the webdriver is quit
            super.remove(name); //remove the old webdriver
            o = super.get(name,objectFactory); //create the new one
        }
        return o;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
    }
}
