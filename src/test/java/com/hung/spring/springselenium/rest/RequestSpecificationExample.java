package com.hung.spring.springselenium.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RequestSpecificationExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
        .setBaseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io")
        .addHeader("x-mock-match-request-headers", "header")
        .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.
                query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
        System.out.println(queryableRequestSpecification.getHeaders());

    }

    @Test
    public void validate_status_code(){
        Response response = get("/get").
                then().log().all().extract().response();
        assertThat(response.getStatusCode(), is(equalTo(200)));
    }

    @Test
    public void validate_response_body(){
        Response response = get("/get").then().log().all().extract().response();
        assertThat(response.getStatusCode(), is(equalTo(200)));
        assertThat(response.path("msg").toString(), equalTo("headerValue1"));
    }
}
