package com.james.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {
    private final Notification notification;

    public NotificationManager(@Qualifier("sms") Notification notification) {
        this.notification = notification;
    }

    public void sendNotification (String message) {
        notification.send(message);
    }
}
