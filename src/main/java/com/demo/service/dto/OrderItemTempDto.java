package com.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrderItemTempDto implements Serializable {
    private static final Long serialVersionUID = 1L;
    private ProductDto product;
    private int quantity;
}
