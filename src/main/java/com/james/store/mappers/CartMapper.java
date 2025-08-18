package com.james.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.james.store.dtos.CartDto;
import com.james.store.dtos.CartItemDto;
import com.james.store.entities.Cart;
import com.james.store.entities.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
  @Mapping(source = "cartItems", target = "items")
  @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
  CartDto toDto(Cart cart);
  @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
  CartItemDto toDto(CartItem cartItem);
}
