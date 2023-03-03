package com.demo.web.api;

import com.demo.service.OrderService;
import com.demo.service.dto.OrderTempDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
public interface OrderResource {
    @Operation(
            summary = "Create order",
            description = "Create an order",
            tags = "order",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create order successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping(value = "/order/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderTempDto> createOrder();

    @Operation(
            summary = "Add product to order",
            description = "Add product to order",
            tags = "order",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add product to order successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Order or product not found", content = @Content)
    })
    @PatchMapping(value = "/order/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<OrderTempDto> addProductToOrder(
            @RequestParam String orderId,
            @RequestParam String sku,
            @RequestParam(required = false) Integer quantity);

    @Operation(
            summary = "Checkout order",
            description = "Checkout order",
            tags = "order",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkout successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @PostMapping("/order/checkout")
    ResponseEntity<OrderService.PaidOrder> checkoutOrder(@RequestParam String orderId, @RequestParam(required = false) String telephone);
}
