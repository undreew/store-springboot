package com.james.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.james.store.dtos.CartDto;
import com.james.store.dtos.CartItemDto;
import com.james.store.entities.Cart;
import com.james.store.entities.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
  // map the cartItems of Cart to items in CartDto
  @Mapping(target = "items", source = "cartItems")
  // using custom expression from the Cart entity to calculate total price and map to totalPrice in CartDto
  @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
  CartDto toDto(Cart cart);
  
  @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
  CartItemDto toDto(CartItem cartItem);
}
