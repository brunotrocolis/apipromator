package com.trocolis.api.promator.model.entity;

import com.trocolis.api.promator.model.domain.ProductStatusDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Esta classe representa a entidade produto
 *
 * @author Bruno Trócolis <bruno.trocolis at gmail.com>
 * @since 2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductStatusDomain status;
    private byte[] image;
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
