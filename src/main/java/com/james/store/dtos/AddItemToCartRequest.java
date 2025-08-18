package com.james.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {
  @NotNull(message = "Product ID cannot be null")
  private Long productId;
}
