package com.micro.customer;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public record CustomerService(CustomerRepository customerRepository) {
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
