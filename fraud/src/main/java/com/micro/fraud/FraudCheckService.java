package com.micro.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckRepository fraudCheckRepository;
    public boolean isFraudulentCustomer(Integer customerID){
        fraudCheckRepository.save(
                FraudCheckHistory.builder()
                        .isFraudster(false)
                        .customerId(customerID)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
