package com.hung.spring.springselenium.theinternettest;

import com.hung.spring.springselenium.SpringBaseTestNGTest;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import com.hung.spring.springselenium.theinternet.downloadfile.DownloadFilePage;
import com.hung.spring.springselenium.theinternet.uploadfile.FileUploadPage;
import com.hung.spring.springselenium.theinternet.uploadfile.UploadConfirmationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FileDownloadTest extends SpringBaseTestNGTest {

    @LazyAutowired
    private DownloadFilePage downloadFilePage;

    @Test
    public void downloadTest() {
        downloadFilePage.goTo();
        assertTrue(downloadFilePage.isReady());

        downloadFilePage.clickFile("chat.pdf");
    }

}
