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
}
