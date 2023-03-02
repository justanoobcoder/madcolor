package com.demo.web.api;

import com.demo.service.dto.ProductDto;
import com.demo.web.vm.ProductVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api")
public interface ProductResource {
    @Operation(
            summary = "Add new product",
            description = "Add new product",
            tags = "product",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Add product successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "409", description = "Product SKU is already used", content = @Content),
    })
    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDto> createProduct(@RequestBody @Valid ProductVM productVM);
}
