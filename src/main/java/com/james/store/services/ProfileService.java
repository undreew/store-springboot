package com.james.store.services;

import com.james.store.entities.Profile;
import com.james.store.repositories.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public void findLoyalProfiles() {
        List<Profile> profiles = profileRepository.findProfileByLoyaltyPoints(2L);
        profiles.forEach(p -> System.out.println("id:" + p.getId() + " , email:" + p.getUser().getEmail()));
    }
}
