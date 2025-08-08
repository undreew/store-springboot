/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.james.store.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author jamesj
 */
@Getter
@AllArgsConstructor
public class ProductDto {
  private final Long id;
  private final String name;
  private final String description;
  private final BigDecimal price;
  private final Byte categoryId;
}
