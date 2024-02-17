package com.micro.fraud;

import com.micro.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
@Slf4j
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;
    @GetMapping("{customerID}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerID") Integer customerID
    ){
        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
        log.info("fraud check response for customer {}", customerID);
        return  new FraudCheckResponse(fraudulentCustomer);
    }
}
