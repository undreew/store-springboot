package com.james.store;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StoreApplication.class, args);
		var orderService = context.getBean(OrderService.class);
		var notifService = context.getBean(NotificationManager.class);
		orderService.placeOrder();
		notifService.sendNotification("Hi");
	}
}
