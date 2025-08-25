package com.james.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.james.store.dtos.CreateProductRequest;
import com.james.store.dtos.ProductDto;
import com.james.store.dtos.UpdateProductRequest;
import com.james.store.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  @Mapping(source = "category.id", target = "categoryId")
  ProductDto toDto(Product product);
  Product toEntity(CreateProductRequest request);
  @Mapping(target = "id", ignore = true)
  void update(UpdateProductRequest request, @MappingTarget Product product);
}
