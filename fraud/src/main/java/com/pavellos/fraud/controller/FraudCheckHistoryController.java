package com.pavellos.fraud.controller;

import com.pavellos.fraud.dto.FraudCheckResponse;
import com.pavellos.fraud.service.FraudCheckHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@Slf4j
public record FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId){
        log.info("Fraud check request for customer: {}", customerId);
        boolean isFraudCustomer = fraudCheckHistoryService.isFraudCustomer(customerId);
        return new FraudCheckResponse(isFraudCustomer);
    }
}
