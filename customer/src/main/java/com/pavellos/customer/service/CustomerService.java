package com.pavellos.customer.service;

import com.pavellos.customer.dto.CustomerRegistrationRequest;
import com.pavellos.customer.dto.FraudCheckResponse;
import com.pavellos.customer.model.Customer;
import com.pavellos.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fName(request.fName())
                .lName(request.lName())
                .email(request.email())
                .build();

        //todo: validate
        //todo: store customer
        //todo: check if fraud
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse == null || fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
    }
}
