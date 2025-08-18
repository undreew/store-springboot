package com.james.store.controllers;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.james.store.dtos.AddItemToCartRequest;
import com.james.store.dtos.CartDto;
import com.james.store.dtos.CartItemDto;
import com.james.store.dtos.UpdateCartItemDto;
import com.james.store.entities.CartItem;
import com.james.store.exceptions.CartNotFoundException;
import com.james.store.mappers.CartMapper;
import com.james.store.repositories.CartRepository;
import com.james.store.repositories.ProductRepository;
import com.james.store.services.CartService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

  private final ProductRepository productRepository;
  private final CartRepository cartRepository;
  private final CartService cartService;
  private final CartMapper cartMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CartDto> createCart() {
    var cart = cartService.createCart();
    return ResponseEntity.ok(cart);
  }
  
  @PostMapping("/{cartId}/items")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CartItemDto> addToCart(@PathVariable(name = "cartId") UUID cartId, @RequestBody AddItemToCartRequest request) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) return ResponseEntity.notFound().build();

    var product = productRepository.findById(request.getProductId()).orElse(null);
    if (product == null) return ResponseEntity.badRequest().build();

    var cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(product.getId())).findFirst().orElse(null);

    if (cartItem != null) {
      cartItem.setQuantity(cartItem.getQuantity() + 1);
    } else {
      cartItem = new CartItem();
      cartItem.setProduct(product);
      cartItem.setQuantity(1);
      cartItem.setCart(cart);
      cart.getCartItems().add(cartItem);
    }

    cartRepository.save(cart);

    var cartItemDto = cartMapper.toDto(cartItem);

    return ResponseEntity.ok(cartItemDto);
  }

  @GetMapping("/{cartId}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<CartDto> getCart(@PathVariable(name = "cartId") UUID cartId) {
    var cartDto = cartService.getCart(cartId);
    return ResponseEntity.ok(cartDto);
  }
  
  @PutMapping("/{cartId}/items/{productId}")
  public ResponseEntity<?> updateCartItem(
    @PathVariable(name = "cartId") UUID cartId,
    @PathVariable(name = "productId") Long productId,
    @Valid @RequestBody UpdateCartItemDto request
  ) {
    var cart = cartRepository.findById(cartId).orElse(null);
    var product = productRepository.findById(productId).orElse(null);

    if (cart == null || product == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Invalid cart or product"));
    if ((request.getQuantity() < 1 || request.getQuantity() > 100)) return ResponseEntity.badRequest().build();
    
    var cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElse(null);

    if (cartItem == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Product not found in cart"));
    
    cartItem.setQuantity(request.getQuantity());
    cartRepository.save(cart);

    return ResponseEntity.ok(cartMapper.toDto(cartItem));
  }

  @DeleteMapping("/{cartId}/items/{productId}")
  public ResponseEntity<?> removeProductFromCart(
    @PathVariable(name = "cartId") UUID cartId,
    @PathVariable(name = "productId") Long productId
  ) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cart not found"));

    var cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElse(null);
    if (cartItem == null) return ResponseEntity.notFound().build();

    cart.getCartItems().remove(cartItem);
    cartRepository.save(cart);
    
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{cartId}/items")
  public ResponseEntity<Void> clearCart(
    @PathVariable(name = "cartId") UUID cartId
  ) {
    var cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) return ResponseEntity.notFound().build();

    cart.getCartItems().removeAll(cart.getCartItems());
    cartRepository.save(cart);

    return ResponseEntity.noContent().build();
  }
  
  @ExceptionHandler(CartNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleCartNotFound() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Cart not found"));
  }

}
