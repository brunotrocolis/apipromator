package com.trocolis.api.promator;

import com.trocolis.api.promator.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Esta classe testa as funcionalidades relacionadas aos produtos
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@SpringBootTest
public class ProductTests {

    @Autowired
    private ProductService productService;


}
