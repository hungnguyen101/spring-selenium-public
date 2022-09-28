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

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

public class AutomateSendAsAFile {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api/").
                        setContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_payload_from_file() {

        File file = new File("src/test/resources/payload.json");

        //update job for user id 2
        String id = "2";

        Response response = with().body(file).
                pathParams("id", id).
                put("/users/{id}");
        assertThat(response.path("name"), matchesPattern("[a-z]+"));
        assertThat(response.path("updatedAt"), matchesPattern("^[0-9a-zA-Z-:.]+$"));
    }

    @Test
    public void validate_post_request_payload_as_map() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "morpheus");
        map.put("job", "zion resident");

        //update job for user id 2
        String id = "2";

        Response response = with().body(map).
                pathParams("id", id).
                put("/users/{id}");
        assertThat(response.path("name"), matchesPattern("[a-z]+"));
        assertThat(response.path("updatedAt"), matchesPattern("^[0-9a-zA-Z-:.]+$"));
    }

    @Test
    public void validate_post_request_payload_json_array_as_list() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "morpheus");
        map.put("job", "zion resident");

        //update job for user id 2
        String id = "2";

        Response response = with().body(map).
                pathParams("id", id).
                put("/users/{id}");
        assertThat(response.path("name"), matchesPattern("[a-z]+"));
        assertThat(response.path("updatedAt"), matchesPattern("^[0-9a-zA-Z-:.]+$"));
    }
}
