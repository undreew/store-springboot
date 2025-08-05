package com.james.store.repositories;

import com.james.store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> fetchProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
