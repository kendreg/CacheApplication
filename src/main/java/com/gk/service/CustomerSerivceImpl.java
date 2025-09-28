package com.gk.service;

import com.gk.entity.Customer;
import com.gk.exception.RecordNotFoundException;
import com.gk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerSerivceImpl implements ICustomerService{

    private final CustomerRepository customerRepository;

    // Spring will inject CustomerRepository automatically
//    public CustomerSerivceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    @CachePut(value="customers", key="#customer.custId" )
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Cacheable(value = "custId", key="#custId")
    @Override
    public Optional<Customer> findById(long custId) {
        log.info("****************** Data fetch from DB************");
        return Optional.ofNullable(customerRepository.findById(custId).orElseThrow(() -> new RecordNotFoundException("Customer id does not exists!")));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(long custId, Customer customer) {
        Customer customer1 = findById(custId).get();
        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustEmailId(customer.getCustEmailId());

        return customerRepository.save(customer1);
    }

    @Override
    @CacheEvict(value="customers",key="#custId")
    public void deleteById(long custId) {
        customerRepository.deleteById(custId);
    }
}
