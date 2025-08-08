package com.james.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.james.store.dtos.ProductDto;
import com.james.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(source = "category.id", target = "categoryId")
  ProductDto toDto(Product product);
}
