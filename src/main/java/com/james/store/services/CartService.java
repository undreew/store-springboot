package com.james.store.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.james.store.dtos.CartDto;
import com.james.store.dtos.CartItemDto;
import com.james.store.entities.Cart;
import com.james.store.exceptions.CartNotFoundException;
import com.james.store.exceptions.ProductNotFoundException;
import com.james.store.mappers.CartMapper;
import com.james.store.repositories.CartRepository;
import com.james.store.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;

  public CartDto createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    return cartMapper.toDto(cart);
  }

  public CartItemDto addToCart(UUID cartId, Long productId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) throw new CartNotFoundException();

    var product = productRepository.findById(productId).orElse(null);
    if (product == null) throw new ProductNotFoundException();

    var cartItem = cart.addItem(product);
    cartRepository.save(cart);

    return cartMapper.toDto(cartItem);
  }

  public CartDto getCart(UUID cartId) {
    var cart = cartRepository.findById(cartId).orElseGet(null);
    if (cart == null) throw new CartNotFoundException();
    return cartMapper.toDto(cart);
  }

  public CartItemDto updateItem(UUID cartId, Long productId, Integer quantity) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) throw new CartNotFoundException();

    var cartItem = cart.getItem(productId);
    if (cartItem == null) throw new ProductNotFoundException();

    cartItem.setQuantity(quantity);
    cartRepository.save(cart);
    return cartMapper.toDto(cartItem);
  }

  public void removeItem(UUID cartId, Long productId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) throw new CartNotFoundException();
    
    cart.removeItem(productId);
    cartRepository.save(cart);
  }

  public void clear(UUID cartId) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) throw new CartNotFoundException();

    cart.clearCart();
    cartRepository.save(cart);
  }

}
