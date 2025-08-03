package com.james.store.services;

import com.james.store.entities.Category;
import com.james.store.entities.Product;
import com.james.store.repositories.CategoryRepository;
import com.james.store.repositories.ProductRepository;
import com.james.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createProductWithCategory() {
        var product = new Product("Product2", new BigDecimal("123.123"));
        var category = new Category("Category2");

        product.setCategory(category);
        var savedProduct = productRepository.save(product);
        category.getProducts().add(product);

        System.out.println(savedProduct);
    }

    @Transactional
    public void createProductForExistingCategory() {
        var category = categoryRepository.findById((byte) 5L).orElseThrow();
        var newProduct = new Product("New Product to Assign with Existing Category", BigDecimal.valueOf(100.25));

        newProduct.setCategory(category);
        productRepository.save(newProduct);
    }

    @Transactional
    public void fetchAllProductsAndAssignToWishlist() {
       var user = userRepository.findById(4L).orElseThrow();
       var products = productRepository.findAll();
       products.forEach(p -> {
           user.getWishlist().add(p);
       });
       userRepository.save(user);
    }

    @Transactional
    public void deleteProduct() {
        productRepository.deleteById(5L);
    }
}
