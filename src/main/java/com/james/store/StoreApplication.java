package com.james.store;

import com.james.store.entities.Address;
import com.james.store.entities.Profile;
import com.james.store.entities.Tag;
import com.james.store.entities.User;
import com.james.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StoreApplication.class, args);
		var rep = ctx.getBean(UserRepository.class);

		rep.findAll().forEach(u -> System.out.println(u.getEmail()));
	}
}
