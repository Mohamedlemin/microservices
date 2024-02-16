package com.micro.clients;

import org.springframework.stereotype.Component;

public record FraudCheckResponse(boolean isFraudster ) {
}
