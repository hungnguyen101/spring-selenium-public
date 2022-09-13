package com.hung.spring.springselenium.repository;

import com.hung.spring.springselenium.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    List<Customer> findByFirstNameStartingWith(String firstName);
    List<Customer> findByDobBetween(Date from, Date to);


}
