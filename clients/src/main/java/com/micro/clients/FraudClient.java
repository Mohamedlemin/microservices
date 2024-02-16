package com.micro.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {
    @GetMapping("/api/v1/fraud-check/{customerID}")
     FraudCheckResponse isFraudster(
            @PathVariable("customerID") Integer customerID
    );
}
