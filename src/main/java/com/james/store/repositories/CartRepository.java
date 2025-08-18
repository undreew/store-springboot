package com.james.store.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.james.store.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
