package com.james.store;

import com.james.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
//		SpringApplication.run(StoreApplication.class, args);
		User user = new User(1L, "james", "james@gmail.com", "123123");
		user.setName("undrew");
	}
}
