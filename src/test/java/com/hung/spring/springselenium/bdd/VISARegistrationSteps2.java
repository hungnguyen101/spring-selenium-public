package com.hung.spring.springselenium.bdd;

import com.hung.spring.springselenium.entity.Customer;
import com.hung.spring.springselenium.repository.CustomerRepository;
import com.hung.spring.springselenium.visa.VisaRegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class VISARegistrationSteps2 {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisaRegistrationPage visaRegistrationPage;

    @Given("I should be able to register VISA")
    public void userWithMRegisterVISA() {
        List<Customer> customers = this.customerRepository.findByFirstNameStartingWith("M")
                .stream()
                .collect(Collectors.toList());

        for(Customer customer:customers){
            this.visaRegistrationPage.goTo();
            this.visaRegistrationPage.setNames(customer.getFirstName(), customer.getLastName());
            this.visaRegistrationPage.setCountryFromAndTo(customer.getFromCountry(), customer.getToCountry());
            this.visaRegistrationPage.setBirthDate(customer.getDob().toLocalDate());
            this.visaRegistrationPage.setContactDetails(customer.getEmail(), customer.getPhone());
            this.visaRegistrationPage.submit();
        }
    }
}
