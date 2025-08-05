package com.james.store;

import com.james.store.entities.Address;
import com.james.store.entities.Profile;
import com.james.store.entities.Tag;
import com.james.store.entities.User;
import com.james.store.repositories.UserRepository;
import com.james.store.services.ProductService;
import com.james.store.services.ProfileService;
import com.james.store.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(StoreApplication.class, args);
		var rep = ctx.getBean(ProductService.class);

		rep.fetchPaginatedProducts(1, 1);
	}
}
