package com.hung.spring.springselenium.rest;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;


public class RestTest {

    @Test
    void validateResponseBody() {
        given().
                baseUri("https://reqres.in/").
                when().request("GET", "/api/users?page=2").
                then().
                log().all().
                assertThat().statusCode(200).
                body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"),
                        "data.first_name", hasItems("Lindsay", "Tobias"),
                        "data.size()", equalTo(6),
                        "data.id", hasItems(7)
                );
    }

    @Test
    void extractRes() {
        String res = given().
                baseUri("https://reqres.in/").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                when().request("GET", "/api/users?page=2").
                then().
                assertThat().statusCode(200).
                extract().
                response().asString();

        JsonPath.from(res).getString("data[0].email");

        System.out.println("response :: " + JsonPath.from(res).getString("data[0].email"));

    }

    @Test
    public void multiple_headers() {

        Header header = new Header("header", "value1");
        Header matchHeader = new Header("x-mock-match-request-headers", "header");

        Headers headers = new Headers(header, matchHeader);

        given().
                baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().log().all().assertThat().statusCode(200);
    }


    @Test
    public void multiple_headers_using_map() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        given().
                baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io").
                headers(headers).
                when().get("/get").
                then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void multi_value_header_in_the_request() {
        Header header1 = new Header("multiHeader", "value1");
        Header header2 = new Header("multiHeader", "header");

        Headers headers = new Headers(header1, header2);
        given().
                baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io").
                headers(headers).
                log().headers().
                when().get("/get").
                then().log().all().assertThat().statusCode(200).
                headers("responseHeader", "value1", "Content-Encoding", "gzip")
        ;
    }

    @Test
    public void extractResponseHeader() {
        Header header1 = new Header("multiHeader", "value1");
        Header header2 = new Header("multiHeader", "header");

        Headers headers = new Headers(header1, header2);
        Headers extractedHeaders = given().
                baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io").
                headers(headers).
                log().headers().
                when().get("/get").
                then().log().all().assertThat().statusCode(200).
                extract().headers();

        /*
        * print all response headers
        *
        * */
        for(Header header:extractedHeaders){
            System.out.println("header name :: " + header.getName() + ", ");
            System.out.println("header value :: " + header.getValue());

        }

/*
        System.out.println("Header name is :: " + extractedHeader.get("responseHeader").getName());
        System.out.println("Header value is :: " + extractedHeader.get("responseHeader").getValue());
        System.out.println("Header name is :: " + extractedHeader.getValue("responseHeader"));
*/
    }

    @Test
    public void extract_multivalue_response_header() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("header", "value1");
        headers.put("x-mock-match-request-headers", "header");

        Headers extractedHeaders = given().
                baseUri("https://cccce435-8ef9-4eed-9b01-00b7d4e4ab77.mock.pstmn.io").
                headers(headers)
        .when()
                .get("/get")
        .then().
                assertThat().
                statusCode(200).extract().headers();

        List<String> values = extractedHeaders.getValues("multiValueHeader");
        for(String value:values){
            System.out.println(
                    value
            );
        }

    }


}
