package com.james.store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements Notification {

    @Override
    public void send(String message) {
        System.out.println("EMAIL");
        System.out.println(message);
    }
}
