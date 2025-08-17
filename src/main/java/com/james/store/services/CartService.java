package com.james.store.services;

import org.springframework.stereotype.Service;

import com.james.store.dtos.CartDto;
import com.james.store.entities.Cart;
import com.james.store.mappers.CartMapper;
import com.james.store.repositories.CartRepository;

@Service
public class CartService {
  private CartRepository cartRepository;
  private CartMapper cartMapper;

  public CartDto createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

}
