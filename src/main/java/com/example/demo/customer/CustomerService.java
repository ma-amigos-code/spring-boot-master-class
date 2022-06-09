package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    List<Customer> getCustomers() {
        log.info("getCustomers was called");
        return customerRepository.findAll();
    }

    Customer getCustomer(Long customerId) {
        return customerRepository
                .findById(customerId)
                .orElseThrow( () -> {
                    NotFoundException notFoundException = new NotFoundException("Customer with ID " + customerId + " not found");
                    log.error("error in getting getCustomer {}", customerId, notFoundException);
                    return notFoundException;
                });
    }

}
