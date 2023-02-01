package com.pavellos.customer.service;

import com.pavellos.clients.fraud.FraudClient;
import com.pavellos.clients.fraud.dto.FraudCheckResponse;
import com.pavellos.clients.notification.NotificationClient;
import com.pavellos.clients.notification.dto.NotificationRequest;
import com.pavellos.customer.dto.CustomerRegistrationRequest;
import com.pavellos.customer.model.Customer;
import com.pavellos.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient,

        NotificationClient notificationClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .fName(request.fName())
                .lName(request.lName())
                .email(request.email())
                .build();

        //todo: validate
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        if (fraudCheckResponse == null || fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to MicroServices...", customer.getFName())
        );
        notificationClient.sendNotification(notificationRequest);
        //todo: send notification
    }
}
