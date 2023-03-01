package com.demo.web.vm;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CategoryVM(
        @Size(max = 50, message = "Category name too long")
        @NotBlank(message = "Category name is required")
        @Schema(example = "Quần đùi")
        String name
) {
}
