package com.hung.spring.springselenium.theinternet.uploadfile;


import com.hung.spring.springselenium.kelvin.annotation.Page;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

@Page
public class FileUploadPage extends Base {

    @FindBy(xpath="//h3[text()='File Uploader']")
    private WebElement title;

    @FindBy(id="file-upload")
    private WebElement btnChooseFile;
    
    @FindBy(id="file-submit")
    private WebElement btnSubmit;


    @Value("${application.the-internet.url}")
    private String url;

    public void goTo(){
        this.driver.get(this.url + "/upload");
    }

    public void chooseFile(String filePath){
        this.btnChooseFile.sendKeys(filePath);
    }
    
    public void submitFile(){
        this.btnSubmit.click();
    }

    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.title.isDisplayed());
    }

}
