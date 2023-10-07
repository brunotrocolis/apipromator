package com.trocolis.api.promator;

import com.trocolis.api.promator.model.domain.ProductStatusDomain;
import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.dto.product.request.RegisterProductRequest;
import com.trocolis.api.promator.model.dto.product.response.RegisterProductResponse;
import com.trocolis.api.promator.model.entity.Product;
import com.trocolis.api.promator.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * Esta classe testa as funcionalidades relacionadas aos produtos
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTests {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private final Product PRODUCT_1 = new Product(
            UUID.randomUUID(),
            "Foo",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            BigDecimal.valueOf(15.99),
            ProductStatusDomain.ACTIVE,
            "\uD83D\uDE00".getBytes(StandardCharsets.UTF_8),
            LocalDateTime.now()
    );

    private final Product PRODUCT_2 = new Product(
            UUID.randomUUID(),
            "Bar",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            BigDecimal.valueOf(1.99),
            ProductStatusDomain.ACTIVE,
            "❤".getBytes(StandardCharsets.UTF_8),
            LocalDateTime.now()
    );

    /**
     * Este método testa o serviço de cadastro de um novo produto com sucesso.
     */
    @Test
    public void testRegisterProduct() {

        givenSalveProduct();

        RegisterProductRequest request =
                new RegisterProductRequest(
                        PRODUCT_1.getName(),
                        PRODUCT_1.getDescription(),
                        PRODUCT_1.getPrice(),
                        PRODUCT_1.getImage()
                );

        ResponseEntity<RegisterProductResponse> response = restTemplate
                .postForEntity("/api/v1/products", request, RegisterProductResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertTrue(response.hasBody());

    }

    /**
     * Este método testa o serviço de busca de todos os produtos.
     */
    @Test
    public void testGetAllProduct() {

        givenGetAllProduct();

        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity("/api/v1/products", ProductDTO[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.hasBody());
        assertEquals(Objects.requireNonNull(response.getBody()).length, 2);

    }


    private void givenSalveProduct() {
        given(productRepository.save(any(Product.class))).willReturn(PRODUCT_1);
    }

    private void givenGetAllProduct() {
        ProductDTO productFoo = new ProductDTO();
        BeanUtils.copyProperties(PRODUCT_1, productFoo);

        ProductDTO productBar = new ProductDTO();
        BeanUtils.copyProperties(PRODUCT_2, productBar);

        given(productRepository.getAll()).willReturn(Arrays.asList(productFoo, productBar));
    }
}
