package com.hung.spring.springselenium.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSchemaValidation {

    @Test
    public void validaJsonSchema() {
        given().
                baseUri("https://postman-echo.com").
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200).body(matchesJsonSchemaInClasspath("json/EchoGet.json"));
    }
}
