package com.james.store;

import com.james.store.entities.Address;
import com.james.store.entities.Profile;
import com.james.store.entities.Tag;
import com.james.store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
//		SpringApplication.run(StoreApplication.class, args);
		User user = new User(1L, "james", "james@gmail.com", "123123");

		user.addTag("Tag1");
		System.out.println(user);

//		Address address = new Address(2L, "Elmo Street", "City", "Zip", user);
//		System.out.println(address.getStreet());
//
//		user.addAddress(address);
//		System.out.println(user);

//		Profile profile = new Profile(1L, "Bio", "09164547960", LocalDate.now(), 100);
//		System.out.println(profile.getLoyaltyPoints());
//
//		Tag tags = new Tag(1L, "human");
//		System.out.println(tags.getName());
	}
}
