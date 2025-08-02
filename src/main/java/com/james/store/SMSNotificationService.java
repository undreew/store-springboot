package com.james.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sms")
@Qualifier
public class SMSNotificationService implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS");
        System.out.println(message);
    }
}
