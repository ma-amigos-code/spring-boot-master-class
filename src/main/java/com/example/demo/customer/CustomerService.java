package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    List<Customer> getCustomers() {
        LOGGER.info("getCustomers was called");
        return customerRepository.findAll();
    }

    Customer getCustomer(Long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow( () -> {
                    NotFoundException notFoundException = new NotFoundException("Customer with ID " + customerId + " not found");
                    LOGGER.error("error in getting getCustomer {}", customerId, notFoundException);
                    return notFoundException;
                });
    }

}
