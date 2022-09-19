package com.hung.spring.springselenium.rest;

import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class MyClass {


    public static void main(String[] args) {

    }


    @Test
    public void requestSpecification() {
        RequestSpecification requestSpecification = given()
                .baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io")
                .headers("x-mock-match-request-headers", "header");

        given().spec(requestSpecification).
                when().get("/get")
                .then().
                assertThat().
                statusCode(200).extract().headers();

    }
}
