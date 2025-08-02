package com.james.store;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

//@Service
public class OrderService {
    // this variable allows us to store the injected class dependency
    private PaymentService paymentService;

    // ways to inject dependency
    // 1. use constructor style
    // NOTE: better for required dependencies and is generally recommended

//    this code uses the Annotative style of BEANS
//    public OrderService(@Qualifier("stripe") PaymentService paymentService) {

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("Order service created");
    }

    @PostConstruct
    public void init () {
        System.out.println("Order service PostConstruct");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Order service PreDestroy");
    }

    public void placeOrder() {
        paymentService.processPayment(20.19);
    }

    // SETTERS
    // 2. use a setter method
    // NOTE: just use for optional dependencies
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
