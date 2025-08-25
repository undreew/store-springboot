package com.james.store.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemDto {
  @NotNull(message = "Quantity must be valid")
  @Min(value = 1, message = "Quantity must be at least 1")
  @Max(value = 10, message = "Quantity must be at most 10")
  private Integer quantity;
}
