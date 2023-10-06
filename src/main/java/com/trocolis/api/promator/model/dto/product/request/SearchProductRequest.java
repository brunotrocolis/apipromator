package com.trocolis.api.promator.model.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductRequest {
    private String keyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
