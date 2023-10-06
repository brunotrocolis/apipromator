package com.trocolis.api.promator.model.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Esta classe representa um DTO de requisição de registro de um novo produto
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */
public record RegisterProductRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        BigDecimal price,
        byte[] image
) {
}
