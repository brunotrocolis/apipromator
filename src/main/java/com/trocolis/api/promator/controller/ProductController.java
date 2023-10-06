package com.trocolis.api.promator.controller;

import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.dto.product.request.RegisterProductRequest;
import com.trocolis.api.promator.model.dto.product.request.SearchProductRequest;
import com.trocolis.api.promator.model.dto.product.response.RegisterProductResponse;
import com.trocolis.api.promator.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<RegisterProductResponse> registerProduct(
            @RequestBody
            @Valid
            RegisterProductRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.registerProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAll());
    }

    @GetMapping("search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestBody
            @Valid
            SearchProductRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.searchProducts(request));
    }
}
