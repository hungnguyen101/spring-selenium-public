package com.hung.spring.springselenium.theinternettest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.theinternet.uploadfile.FileUploadPage;
import com.hung.spring.springselenium.theinternet.uploadfile.UploadConfirmationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FileUploadTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private FileUploadPage fileUploadPage;

    @LazyAutowired
    private UploadConfirmationPage uploadConfirmationPage;

    @Test
    public void uploadTest() {
        fileUploadPage.goTo();
        assertTrue(fileUploadPage.isReady());

        fileUploadPage.chooseFile("/Users/ruchaburu/spring-selenium/chat.pdf");
        fileUploadPage.submitFile();
        assertTrue(uploadConfirmationPage.isReady());
    }

}
