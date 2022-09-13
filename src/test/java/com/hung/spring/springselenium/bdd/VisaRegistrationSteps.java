package com.hung.spring.springselenium.bdd;

import com.hung.spring.springselenium.repository.CustomerRepository;
import com.hung.spring.springselenium.visa.VisaRegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.time.LocalDate;

public class VisaRegistrationSteps {

    @Autowired
    private VisaRegistrationPage registrationPage;

    @Given("I am on registration form")
    public void launchSite() {
        registrationPage.goTo();
        Assert.assertTrue(registrationPage.isReady());
    }

    @When("I select my from country {string} and to country {string}")
    public void selectCountry(String from, String to) {
        this.registrationPage.setCountryFromAndTo(from, to);
    }

    @And("I enter my dob as {string}")
    public void enteDob(String dob) {
        this.registrationPage.setBirthDate(LocalDate.parse(dob));
    }

    @And("I enter my name as {string} and {string}")
    public void enterNames(String fn, String ln) {
        this.registrationPage.setNames(fn, ln);
    }

    @And("I enter my contact details as {string} and {string}")
    public void enterContactDetails(String email, String phone) {
        this.registrationPage.setContactDetails(email, phone);
    }

    @And("I enter the comment {string}")
    public void enterComment(String comment) {
        this.registrationPage.setComments(comment);
    }

    @And("I submit the form")
    public void submit() {
        this.registrationPage.submit();
    }

    @Then("I should see the confirmation number")
    public void getConfirmationNumber() {
        StringUtils.isEmpty(this.registrationPage.getConfirmationNumber().trim());
    }
}
