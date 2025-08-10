package com.james.store.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemDto {
  @NotNull
  private Integer quantity;
}
