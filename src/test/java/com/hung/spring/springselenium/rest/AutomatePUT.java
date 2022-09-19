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

public class AutomatePUT {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_put_bdd_style(){
        //update job for user id 2
        String id = "2";

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given().
                body(payload).
                pathParam("id", id)
                .when().put("/users/{id}")
                .then().assertThat()
                .body("job", equalTo("zion resident"),
                        "updatedAt", matchesPattern("^[0-9a-zA-Z-:.]+$"));

    }

    @Test
    public void validate_put_non_bdd_style(){
        //update job for user id 2
        String id = "2";

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        Response response = with().body(payload).
                pathParams("id", id).
                put("/users/{id}");
        assertThat(response.path("name"), matchesPattern("[a-z]+"));
        assertThat(response.path("updatedAt"), matchesPattern("^[0-9a-zA-Z-:.]+$"));
    }
}
