package com.hung.spring.springselenium;

import com.hung.spring.springselenium.entity.Customer;
import com.hung.spring.springselenium.repository.CustomerRepository;
import com.hung.spring.springselenium.visa.VisaRegistrationPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Iterator;

public class CustomerVisaTest extends SpringBaseTestNGTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisaRegistrationPage visaRegistrationPage;

    @Test(dataProvider = "getData")
    public void visaTest(Customer customer) {
        this.visaRegistrationPage.goTo();
        this.visaRegistrationPage.setNames(customer.getFirstName(), customer.getLastName());
        this.visaRegistrationPage.setCountryFromAndTo(customer.getFromCountry(), customer.getToCountry());
        this.visaRegistrationPage.setBirthDate(customer.getDob().toLocalDate());
        this.visaRegistrationPage.setContactDetails(customer.getEmail(), customer.getPhone());
        this.visaRegistrationPage.submit();

        System.out.println(
                this.visaRegistrationPage.getConfirmationNumber()
        );
    }

    @DataProvider
    public Object[] getData() {
        return this.customerRepository
                .findByDobBetween(Date.valueOf("2006-08-14"), Date.valueOf("2009-08-14"))
                .stream()
                .limit(3)
                .toArray();
    }
}
