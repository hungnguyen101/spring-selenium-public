package com.hung.spring.springselenium.rest;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class UploadFile {
    
    @Test
    public void multipart_form_data() {

        //Use this web to convert base64 to pdf https://www.ipvoid.com/base64-to-pdf/

        String attributes = "{\"name\":\"chat.pdf\",\"parent\":{\"id\":\"123456\"}}";

        given().
                baseUri("https://postman-echo.com").
                multiPart("file", new File("chat.pdf")).
                multiPart("attributes", attributes, "application/pdf").
                log().all().
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }


    //https://github.com/appium-boneyard/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk

    @Test
    public void download_file() throws IOException {

        //Use this web to convert base64 to pdf https://www.ipvoid.com/base64-to-pdf/
        InputStream is =
        given().
                baseUri("https://github.com").
                log().all().
        when().
                get("/appium-boneyard/sample-code/raw/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
        then().
                log().all().
                extract().response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();
    }

    @Test
    public void form_url_encoded() {

        //Use this web to convert base64 to pdf https://www.ipvoid.com/base64-to-pdf/


        given().
                baseUri("https://postman-echo.com").
                config(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("key1", "bar1").
                formParam("key 2", "bar 2").
                log().all().
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
