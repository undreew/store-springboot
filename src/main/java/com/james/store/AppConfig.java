package com.james.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

// this class means, that this is a source of BEAN definitions
@Configuration
public class AppConfig {
    @Value("${spring.application.paymentService}")
    private String paymentService;

    // name of beans should be a noun
    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PaypalService();
    }

    @Bean
    // can add scope, by default it is a singleton
    @Scope("singleton")
    public OrderService orderService() {
        return new OrderService(Objects.equals(paymentService, "stripe") ? stripe() : paypal());
    }
}
