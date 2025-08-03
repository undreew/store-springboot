package com.james.store.services;

import com.james.store.entities.Address;
import com.james.store.entities.User;
import com.james.store.repositories.AddressRepository;
import com.james.store.repositories.ProfileRepository;
import com.james.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var newUser = new User("name", "name@gmail.com", "231231");

        if (entityManager.contains(newUser)) System.out.println("Persistent");
        else System.out.println("Transient / Detached");

        userRepository.save(newUser);

        if (entityManager.contains(newUser)) System.out.println("Persistent");
        else System.out.println("Transient / Detached");
    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated() {
        var user = new User("User1", "user1@email.com", "123123");
        var address = new Address("Elmo", "Manila", "3018");

        user.addAddress(address);
        userRepository.save(user);
    }

    public void deleteRelated() {
        userRepository.deleteById(9L);
    }

    @Transactional
    public void fetchUserWithAddress() {
        var user = userRepository.findById(2L).orElseThrow();
        System.out.println(user);
    }
}
