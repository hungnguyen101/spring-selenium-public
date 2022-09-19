package com.hung.spring.springselenium.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class AutomateDELETE {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api/").
                setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(204);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_delete_bdd_style(){

        String id = "3";

        given().pathParam("id", id)
                .when().delete("/users/{id}").then();

    }

    @Test
    public void validate_delete_non_bdd_style(){
        String id = "3";

        Response response = with().
                pathParams("id", id).
                delete("/users/{id}");
    }
}
