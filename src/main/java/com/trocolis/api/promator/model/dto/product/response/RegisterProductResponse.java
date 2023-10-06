package com.trocolis.api.promator.model.dto.product.response;

import com.trocolis.api.promator.model.domain.ProductStatusDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Esta classe reprensenta um DTO de resposta ao registro de um novo produto
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProductResponse {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductStatusDomain status;
    private byte[] image;
}
