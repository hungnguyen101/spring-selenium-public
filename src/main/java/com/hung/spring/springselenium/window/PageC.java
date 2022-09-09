package com.hung.spring.springselenium.window;

import com.hung.spring.springselenium.kelvin.annotation.LazyConfiguration;
import com.hung.spring.springselenium.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@LazyConfiguration
public class PageC  extends Base {

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
