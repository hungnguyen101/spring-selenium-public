package com.hung.spring.springselenium.window;

import com.hung.spring.springselenium.kelvin.annotation.Window;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Window("Page B")
public class PageB  extends Base {

    @FindBy(id = "area" )
    private WebElement textArea;

    public void addToArea(final String msg){
        this.textArea.sendKeys(msg);
    }

    @Override
    public boolean isReady() {
        return this.wait.until((d) -> this.textArea.isDisplayed());
    }
}
