package com.hung.spring.springselenium.google;

import com.hung.spring.springselenium.kelvin.annotation.Page;
import com.hung.spring.springselenium.page.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
public class GooglePage extends Base {

    private static final Logger logger = LoggerFactory.getLogger(GooglePage.class);

    @Autowired
    private SearchComponent searchComponent;

    @Autowired
    private SearchResult searchResult;

    @Value("${application.url}")
    private String url;

    public void goTo(){
        this.driver.get(this.url);
        logger.info("INFO ### " + this.url);
    }

    public void close(){
        this.driver.quit();
    }

    public SearchComponent getSearchComponent() {
        return searchComponent;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    @Override
    public boolean isReady() {
        return this.searchComponent.isReady();
    }


}
