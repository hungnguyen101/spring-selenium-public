package com.hung.spring.springselenium.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import simplepojo.SimplePoJo;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JacksonAPI_JSONArray {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io")
                //.addHeader("x-mock-match-request-body", "true")
                .setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_post_request_payload_json_array_as_list() throws JsonProcessingException {

        HashMap<String, String> obj5001 = new HashMap<String, String>();
        obj5001.put("id", "5001");
        obj5001.put("type", "None");

        HashMap<String, String> obj5002 = new HashMap<String, String>();
        obj5002.put("id", "5002");
        obj5002.put("type", "Glazed");

        ArrayList<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
        jsonList.add(obj5001);
        jsonList.add(obj5002);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonListStr = objectMapper.writeValueAsString(jsonList);

        given().body(jsonListStr).
                when().post("/post").
                then().log().all().
                assertThat().body("msg", equalTo("Success"));
    }

    @Test
    public void simple_POJO_example(){

        //SimplePoJo simplePojo = new SimplePoJo("value1","value2");
        //jackson will convert pojo class to json

        SimplePoJo simplePojo = new SimplePoJo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");

        given().body(simplePojo).
                when().
                    post("/postSimpleJson").
                then().
                    spec(responseSpecification).
                    assertThat().body("key1", equalTo(simplePojo.getKey1()),
                "key2", equalTo(simplePojo.getKey2()));
    }

    @Test
    public void deserialize_POJO() throws JsonProcessingException {

        SimplePoJo simplePojo = new SimplePoJo("value1","value2");

        SimplePoJo deserializePojo = given()
                .body(simplePojo).
        when().
                post("/postSimpleJson").
        then().spec(responseSpecification)
                .extract()
                .response()
                .as(SimplePoJo.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String deserializePojoStr = objectMapper.writeValueAsString(deserializePojo);
        System.out.println(deserializePojoStr);
        String simplePojoStr = objectMapper.writeValueAsString(simplePojo);
        System.out.println(simplePojoStr);

        assertThat(objectMapper.readTree(deserializePojoStr), equalTo(objectMapper.readTree(simplePojoStr)));
    }


}
