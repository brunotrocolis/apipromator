package com.trocolis.api.promator.service;

import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.dto.product.request.RegisterProductRequest;
import com.trocolis.api.promator.model.dto.product.request.SearchProductRequest;
import com.trocolis.api.promator.model.dto.product.response.RegisterProductResponse;
import com.trocolis.api.promator.model.entity.Product;
import com.trocolis.api.promator.model.repository.ProductRepository;
import com.trocolis.api.promator.model.repository.helper.ProductDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

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

    @Autowired
    private ProductDao productDao;

    private static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());

    /**
     * Este método cadastra novos produtos
     *
     * @param request
     * @return Novo produto
     */
    public RegisterProductResponse registerProduct(RegisterProductRequest request) {
        var product = new Product();

        LOGGER.info(request.toString());

        BeanUtils.copyProperties(request, product);

        product = productRepository.save(product);

        var response = new RegisterProductResponse();

        BeanUtils.copyProperties(product, response);

        return response;
    }

    /**
     * Este Método consulta todos os produtos
     *
     * @return Lista com todos os produtos
     */
    public List<ProductDTO> getAll() {
        LOGGER.info("Consultando todos os produtos");
        return productRepository.getAll();
    }

    /**
     * Este método busca produtos por palavra chame e/ou faixa de preço
     *
     * @return Lista de produtos
     */
    public List<ProductDTO> searchProducts(SearchProductRequest request) {
        return productDao.searchProducts(request);
    }
}
