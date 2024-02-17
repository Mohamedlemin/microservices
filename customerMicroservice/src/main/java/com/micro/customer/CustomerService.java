package com.micro.customer;

import com.micro.clients.FraudCheckResponse;
import com.micro.clients.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
//	private final FraudClient fraudClient;
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();

		//todo : check if email is valide
		//todo : check if email is not taken
//		FraudCheckResponse fraudsterResponse = fraudClient.isFraudster(customer.getId());
//		if (fraudsterResponse.isFraudster()){
//			throw new IllegalStateException("Fraudster");
//		}
		customerRepository.save(customer);
	}
}
