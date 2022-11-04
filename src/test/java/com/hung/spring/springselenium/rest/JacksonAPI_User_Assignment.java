package com.hung.spring.springselenium.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import simplepojo.Address;
import simplepojo.Geo;
import simplepojo.UserRoot;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JacksonAPI_User_Assignment {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                //.addHeader("x-mock-match-request-body", "true")
                .setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }


    @Test(dataProvider = "user")
    public void serialize_deserialize_User(UserRoot userRoot, int userID) throws JsonProcessingException {

/*
        Geo geo = new Geo("-37.3159", "81.1496");
        Address add = new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", geo);
        UserRoot userRoot = new UserRoot("Leanne Graham", "Bret", "Sincere@april.biz", add);
*/


        UserRoot deserializeUserRoot = given()
                .body(userRoot).
                        when().
                        post("/users").
                        then().spec(responseSpecification)
                .extract()
                .response()
                .as(UserRoot.class);

        assertThat(deserializeUserRoot.getId(), equalTo(userID));
    }

    @DataProvider(name = "user")
    public Object[][] getUserData() {

        return new Object[][]{
                {new UserRoot("Leanne Graham", "Bret", "Sincere@april.biz", new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496"))), 11},
                {new UserRoot("Patricia Lebsack", "Karianne", "Julianne.OConner@kory.org", new Address("Hoeger Mall", "Apt. 692", "South Elvis", "53919-4257", new Geo("29.4572", "-164.2990"))), 11}
        };
    }


}
