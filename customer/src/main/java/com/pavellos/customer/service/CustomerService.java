package com.pavellos.customer.service;

import com.pavellos.customer.dto.CustomerRegistrationRequest;
import com.pavellos.customer.model.Customer;
import com.pavellos.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fName(request.fName())
                .lName(request.lName())
                .email(request.email())
                .build();

        //todo: validate
        //todo: store customer

        customerRepository.save(customer);
    }
}
