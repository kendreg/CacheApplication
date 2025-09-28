package com.gk.service;

import com.gk.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    Customer save(Customer customer);

    Optional<Customer> findById(long custId);

    List<Customer> findAll();

    Customer update(long custId, Customer customer);

    void deleteById(long custId);
}
