package com.trocolis.api.promator.model.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Esta classe representa um DTO de requisição de busca por produtos
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */
public record SearchProductRequest(
        @NotBlank
        String keyword,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {
}
