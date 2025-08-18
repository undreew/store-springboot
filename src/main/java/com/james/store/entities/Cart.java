package com.james.store.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "date_created", insertable = false, updatable = false)
  private LocalDate dateCreated;

  // cascase merge allows to update an existing record by updating the cart items
  // orphanRemoval allows to delete cart item records when they are removed from the cart
  @OneToMany(mappedBy = "cart", cascade = CascadeType.MERGE, orphanRemoval = true)
  private Set<CartItem> cartItems = new LinkedHashSet<>();

  public BigDecimal getTotalPrice() {
    BigDecimal totalPrice = BigDecimal.ZERO;

    for (CartItem cartItem : cartItems) {
      BigDecimal itemTotal = cartItem.getTotalPrice();
      totalPrice = totalPrice.add(itemTotal);
    }

    return totalPrice;
  }

  public CartItem getItem(Long productId) {
    return cartItems.stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElse(null);
  }

  public CartItem addItem(Product product) {
    var cartItem = getItem(product.getId());
    if (cartItem != null) {
      cartItem.setQuantity(cartItem.getQuantity() + 1);
    } else {
      cartItem = new CartItem();
      cartItem.setProduct(product);
      cartItem.setQuantity(1);
      cartItem.setCart(this);
      cartItems.add(cartItem);
    }
    return cartItem;
  }

  public void removeItem(Long productId) {
    var cartItem = getItem(productId);
    if (cartItem != null) {
      cartItems.remove(cartItem);
      cartItem.setCart(null); // clear the cart reference
    }
  }

  public void clearCart() {
    cartItems.clear();
  }

}
