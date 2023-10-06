package com.trocolis.api.promator.model.repository;

import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT new com.trocolis.api.promator.model.dto.product.ProductDTO(p.id, p.name, p.description, p.price, p.status, p.image, p.creationDateTime) FROM Product p")
    List<ProductDTO> getAll();

}
