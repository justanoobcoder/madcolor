package com.demo.web.vm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record ProductVM(
        @Size(max = 20, message = "SKU too long")
        @NotBlank(message = "Product SKU is required")
        String sku,
        @NotBlank(message = "Product name is required")
        String name,
        @NotNull(message = "Category is required")
        Long categoryId,
        @NotNull(message = "Price is required")
        Double price,
        @NotNull(message = "Quantity is required")
        Integer quantity) {
}
