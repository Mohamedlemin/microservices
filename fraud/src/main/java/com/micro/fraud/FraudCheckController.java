package com.micro.fraud;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud-check")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;
    @GetMapping("{customerID}")
    public FraudCheckResponse isFraudster(
            @PathVariable("customerID") Integer customerID
    ){
        boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
        return  new FraudCheckResponse(fraudulentCustomer);
    }
}
