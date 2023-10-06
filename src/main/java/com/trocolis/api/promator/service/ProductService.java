package com.trocolis.api.promator.service;

import com.trocolis.api.promator.model.dto.product.request.RegisterProductRequest;
import com.trocolis.api.promator.model.dto.product.response.RegisterProductResponse;
import com.trocolis.api.promator.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que implemanta as regras de negócio relacionadas aos produtos
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Este método cadastra novos produtos
     *
     * @param request
     * @return Novo produto
     */
    public RegisterProductResponse registerProduct(RegisterProductRequest request) {

        // TODO: Implementar o cadastro de novos produtos

        return null;
    }

}
