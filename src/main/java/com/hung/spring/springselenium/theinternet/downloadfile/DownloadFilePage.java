package com.hung.spring.springselenium.theinternet.downloadfile;

import com.hung.spring.springselenium.kelvin.annotation.Page;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

@Page
public class DownloadFilePage extends Base {
    
    @FindBy(xpath="//h3[text()='File Downloader']")
    private WebElement title;


    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.title.isDisplayed());
    }

    @Value("${application.the-internet.url}")
    private String url;

    public void goTo(){
        this.driver.get(this.url + "/download");
    }

    public void clickFile(String fileName){
        this.driver.findElement(new By.ByPartialLinkText(fileName)).click();
    }
}
