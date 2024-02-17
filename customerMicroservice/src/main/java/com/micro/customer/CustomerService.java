package com.micro.customer;

import com.micro.amqp.RabbitMQMessageProducer;
import com.micro.clients.fraud.FraudCheckResponse;
import com.micro.clients.fraud.FraudClient;
import com.micro.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final FraudClient fraudClient;
	private final RabbitMQMessageProducer rabbitMQMessageProducer;


	public CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, RabbitMQMessageProducer rabbitMQMessageProducer) {
		this.customerRepository = customerRepository;
		this.fraudClient = fraudClient;
		this.rabbitMQMessageProducer = rabbitMQMessageProducer;
	}

	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		// todo: check if email valid
		// todo: check if email not taken
		customerRepository.saveAndFlush(customer);

		FraudCheckResponse fraudCheckResponse =
				fraudClient.isFraudster(customer.getId());

		if (fraudCheckResponse.isFraudster()) {
			throw new IllegalStateException("fraudster");
		}

		NotificationRequest notificationRequest = new NotificationRequest(
				customer.getId(),
				customer.getEmail(),
				String.format("Hi %s, welcome to Amigoscode...",
						customer.getFirstName())
		);
		rabbitMQMessageProducer.publish(
				notificationRequest,
				"internal.exchange",
				"internal.notification.routing-key"
		);

	}
}
