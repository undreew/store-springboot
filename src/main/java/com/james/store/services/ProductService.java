package com.james.store.services;

import com.james.store.entities.Category;
import com.james.store.entities.Product;
import com.james.store.repositories.CategoryRepository;
import com.james.store.repositories.ProductRepository;
import com.james.store.repositories.UserRepository;
import com.james.store.repositories.specifications.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    @Transactional
    public void updateProductByCategory() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(10.99), (byte) 4);
    }

    public void fetchProducts() {
        var products = productRepository.findByCategory(new Category((byte) 1));
    }

    public void fetchProductsByCriteria() {
        var products = productRepository.fetchProductsByCriteria("Product1", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecs(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll().forEach(System.out::println);
    }

    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(Sort.by("price").descending());
        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNum, int size) {
        PageRequest pageRequest = PageRequest.of(pageNum, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var pages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println("Total pages: " + pages);
        System.out.println("Total elements: " + totalElements);
    }
}
