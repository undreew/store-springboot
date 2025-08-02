package com.james.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//@Service("paypal")
//@Qualifier
public class PaypalService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("PAYPAL");
        System.out.println("Amount: " + amount + " paid.");
    }
}
