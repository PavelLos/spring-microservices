package com.pavellos.fraud.service;

import com.pavellos.fraud.model.FraudCheckHistory;
import com.pavellos.fraud.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckHistoryService(FraudCheckHistoryRepository fraudCheckHistoryRepository) {

    public boolean isFraudCustomer(Long customerId){
        fraudCheckHistoryRepository.save(FraudCheckHistory.builder()
                .isFraudster(false)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build());
        // Just for test purposes. No real logic
        return false;
    }

}
