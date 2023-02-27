package com.demo.web.vm;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CustomerVM(
    @NotNull(message = "Telephone is required")
    @Pattern(regexp = "^\\d{9,12}$", message = "Invalid telephone")
    @Schema(example = "0911124536")
    String telephone,
    @NotBlank(message = "Full name must not be empty")
    @Pattern(regexp = "^[a-zA-Z ]{3,50}$", message = "Invalid name")
    @Schema(example = "Tran Huu Chien")
    String fullName,
    @NotNull
    @Pattern(regexp = "^(male|female)$", message = "Gender must be 'male' or 'female'")
    @Schema(example = "male")
    String gender
) {}
