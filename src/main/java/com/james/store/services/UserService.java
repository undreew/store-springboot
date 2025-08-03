package com.james.store.services;

import com.james.store.entities.User;
import com.james.store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
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

    public void showRelatedEntities() {
        var user = userRepository.findById(2L).orElseThrow();
        System.out.println(user.getEmail());
    }
}
