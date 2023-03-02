package com.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.demo.domain.Product} entity
 */
@Getter
@Setter
public class ProductDto implements Serializable {
    private String sku;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer discountPercent;
    private String status;
    private Integer quantity;
}