package com.trocolis.api.promator.model.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Esta classe representa um DTO do requisição de registro de um novo produto
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private byte[] image;
}
