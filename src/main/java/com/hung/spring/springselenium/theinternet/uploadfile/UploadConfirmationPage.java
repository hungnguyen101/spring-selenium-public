package com.hung.spring.springselenium.theinternet.uploadfile;


import com.hung.spring.springselenium.kelvin.annotation.Page;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

@Page
public class UploadConfirmationPage extends Base {

    @FindBy(xpath="//h3[text()='File Uploaded!']")
    private WebElement confirmedText;


    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.confirmedText.isDisplayed());
    }

}
