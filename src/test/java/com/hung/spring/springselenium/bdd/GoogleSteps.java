package com.hung.spring.springselenium.bdd;

import com.hung.spring.springselenium.google.GooglePage;
import com.hung.spring.springselenium.kelvin.annotation.LazyAutowired;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
@CucumberContextConfiguration
public class GoogleSteps {

    @LazyAutowired
    private GooglePage googlePage;

    @Given("I am on google site")
    public void launchSite() {
        this.googlePage.goTo();
    }

    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) {
        this.googlePage.getSearchComponent().search(keyword);
    }

    @And("I should see the search results page")
    public void clickSearch() {
        Assert.assertTrue(this.googlePage.getSearchResult().isReady());
    }

    @Then("I should see at least {int} results")
    public void verifyResults(int count) {
        Assert.assertTrue(this.googlePage.getSearchResult().getResultCount() > count);
    }

    @Then("I quit browser")
    public void quit() {
        this.googlePage.close();
    }
}
