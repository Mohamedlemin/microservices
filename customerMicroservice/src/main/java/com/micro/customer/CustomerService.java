package com.micro.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();

		//todo : check if email is valide
		//todo : check if email is not taken
		customerRepository.save(customer);
	}
}
