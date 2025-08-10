package com.james.store.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateProductRequest {
  private String name;
  private Byte categoryId;
  private BigDecimal price;
  private String description;
}
