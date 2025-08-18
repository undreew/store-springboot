package com.james.store.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.james.store.dtos.CartDto;
import com.james.store.entities.Cart;
import com.james.store.exceptions.CartNotFoundException;
import com.james.store.mappers.CartMapper;
import com.james.store.repositories.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;

  public CartDto createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public CartDto getCart(UUID cartId) {
    var cart = cartRepository.findById(cartId).orElseGet(null);
    if (cart == null) throw new CartNotFoundException();
    return cartMapper.toDto(cart);
  }

}
