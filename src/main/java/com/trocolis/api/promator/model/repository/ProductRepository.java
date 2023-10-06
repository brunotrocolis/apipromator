package com.trocolis.api.promator.model.repository;

import com.trocolis.api.promator.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

//    @Query("SELECT p FROM Product p WHERE p.name LIKE %request.keyword% OR p.description LIKE %request.keyword%")
//    List<Product> searchProducts(SearchProductRequest request);
}
