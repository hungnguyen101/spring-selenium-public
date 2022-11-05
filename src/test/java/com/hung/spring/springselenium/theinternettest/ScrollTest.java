package com.hung.spring.springselenium.theinternettest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.theinternet.scroll.AmazonPage;
import com.hung.spring.springselenium.theinternet.uploadfile.FileUploadPage;
import com.hung.spring.springselenium.theinternet.uploadfile.UploadConfirmationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ScrollTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private AmazonPage amazonPage;


    @Test
    public void scrollTest() {
        amazonPage.goTo();
        assertTrue(amazonPage.isReady());


        amazonPage.searchProduct("Hung");

        amazonPage.submit();



//        amazonPage.scrollDown1000();
//
//        amazonPage.scrollToTopSeller();
//        assertTrue(amazonPage.isTopSellerDisplay());
//
//        amazonPage.scrollToBottom();
//        assertTrue(amazonPage.isCopyRightDisplay());
    }

}
