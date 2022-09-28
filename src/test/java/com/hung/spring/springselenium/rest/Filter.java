package com.hung.spring.springselenium.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class Filter {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        //useful in CI
        //for debugging purposes
        //useful with multiple tests, reuse
        PrintStream FileOutputStream = new PrintStream(new File("restAssured.log"));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new RequestLoggingFilter(LogDetail.BODY, FileOutputStream));
        requestSpecBuilder.addFilter(new ResponseLoggingFilter(LogDetail.STATUS, FileOutputStream));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void loggingFilter() throws FileNotFoundException {
        given(requestSpecification).
                baseUri("https://postman-echo.com").
        when().
                get("/get").
        then().spec(responseSpecification).
                assertThat().
                statusCode(200);
    }
}
