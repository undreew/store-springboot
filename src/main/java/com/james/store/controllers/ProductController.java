package com.james.store.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.james.store.dtos.CreateProductRequest;
import com.james.store.dtos.ProductDto;
import com.james.store.dtos.UpdateProductRequest;
import com.james.store.entities.Category;
import com.james.store.entities.Product;
import com.james.store.mappers.ProductMapper;
import com.james.store.repositories.CategoryRepository;
import com.james.store.repositories.ProductRepository;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    @GetMapping
    public List<ProductDto> index (@RequestParam(required = false, name = "categoryId") Byte categoryId) {
      List<Product> products;
      if (categoryId != null) {
        products = productRepository.findByCategoryId(categoryId);
      } else {
        products = productRepository.findAllWithCategory();
      }
      return products.stream().map(product -> productMapper.toDto(product)).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request) {
      Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

      if (category == null) return ResponseEntity.badRequest().build();
      
      var product = productMapper.toEntity(request);
      product.setCategory(category);
      productRepository.save(product);
      return ResponseEntity.ok(productMapper.toDto(product));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
      if (id == null) return ResponseEntity.badRequest().build();

      Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
      var product = productRepository.findById(id).orElse(null);

      if (product == null || category == null) return ResponseEntity.notFound().build();

      productMapper.update(request, product);
      product.setCategory(category);
      productRepository.save(product);

      return ResponseEntity.ok(productMapper.toDto(product)); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
      if (id == null) return ResponseEntity.badRequest().build();

      Product product = productRepository.findById(id).orElse(null);

      if (product == null) return ResponseEntity.notFound().build();

      productRepository.delete(product);
      return ResponseEntity.noContent().build();
    }

}
