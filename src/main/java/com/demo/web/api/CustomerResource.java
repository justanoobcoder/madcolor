package com.demo.web.api;

import com.demo.service.dto.CustomerDto;
import com.demo.web.vm.CustomerVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
public interface CustomerResource {
    @Operation(
            summary = "Add a new customer",
            description = "Add a new customer",
            tags = "customer",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added customer successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - missing or invalid JWT token", content = @Content),
            @ApiResponse(responseCode = "409", description = "Telephone already existed", content = @Content)
    }
    )
    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto createNewCustomer(@RequestBody @Valid CustomerVM customerVM);

    @Operation(
            summary = "Get a customer",
            description = "Get a customer by telephone",
            tags = "customer",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get customer successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - missing or invalid JWT token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
    }
    )
    @GetMapping(value = "/customers/{telephone}", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto getCustomer(@PathVariable String telephone);

    @Operation(
            summary = "Update a customer",
            description = "Update a customer",
            tags = "customer",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update customer successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized - missing or invalid JWT token", content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Telephone already existed", content = @Content)
    }
    )
    @PatchMapping(value = "/customers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerVM customerVM);
}
