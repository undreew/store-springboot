package com.james.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


//@Service("stripe")
//@Primary
public class StripePaymentService implements PaymentService {
    @Value("${stripe.apiUrl}")
    private String apiURL;

    @Value("${stripe.enabled}")
    private boolean enabled;

    // the 3000 is the fallback value in case the left value is not declared in the yaml file
    @Value("${stripe.timeout:3000}")
    private int timeout;

    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("STRIPE");
        System.out.println(apiURL);
        System.out.println(enabled);
        System.out.println(timeout);
        System.out.println(supportedCurrencies);
        System.out.println("Amount: " + amount + " paid.");
    }
}
