package com.trocolis.api.promator;

import com.trocolis.api.promator.model.domain.product.ProductStatusDomain;
import com.trocolis.api.promator.model.dto.product.ProductDTO;
import com.trocolis.api.promator.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    private final TestRestTemplate restTemplate;


    public ProductTests(@Autowired TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


//    @Test
//    public void testGetAllProduct() {
//        given(productRepository.getAll())
//                .willReturn(Arrays.asList(
//                        new ProductDTO(
//                                UUID.randomUUID(),
//                                "Coisa",
//                                "Produto Teste",
//                                BigDecimal.valueOf(15.99),
//                                ProductStatusDomain.ACTIVE,
//                                null,
//                                LocalDateTime.now()
//                        ),
//                        new ProductDTO(
//                                UUID.randomUUID(),
//                                "Outra Coisa",
//                                "Produto Teste",
//                                BigDecimal.valueOf(1.99),
//                                ProductStatusDomain.ACTIVE,
//                                null,
//                                LocalDateTime.now()
//                        )
//                ));
//
//        ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity("/api/v1/products", ProductDTO[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//    }


}
