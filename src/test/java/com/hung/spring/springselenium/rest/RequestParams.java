package com.hung.spring.springselenium.rest;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RequestParams {

    @Test
    public void single_query_parameter() {
        given().
                baseUri("https://postman-echo.com").
                //param("foo1", "bar1").
                        queryParams("foo2", "bar2").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multiple_query_parameter() {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("foo2", "bar2");
        paramMap.put("foo3", "bar3");


        given().
                baseUri("https://postman-echo.com").
                //param("foo1", "bar1").
                        queryParams(paramMap).
                //queryParam("foo3", "bar3").
                        log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multi_value_query_parameter() {
        given().
                baseUri("https://postman-echo.com").
                //param("foo1", "bar1").
                        queryParam("foo1", "bar1;bar2;bar3").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void path_parameter() {
        given().
                baseUri("https://reqres.in").
                pathParams("userId", "1").
                log().all().
                when().
                get("/api/users/{userId}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multipart_form_data() {
        given().
                baseUri("https://postman-echo.com").
                multiPart("foo1", "bar1"). //control is nothing, but a usual key
                log().all().
                when().
                post("/post").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
