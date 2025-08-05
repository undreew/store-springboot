package com.james.store.repositories;

import com.james.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @EntityGraph(attributePaths = "user")
    @Query("select p from Profile p where p.loyaltyPoints > :points")
    List<Profile> findProfileByLoyaltyPoints(@Param("points") Long points);
}

