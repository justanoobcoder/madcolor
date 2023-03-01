package com.demo.web.api;

import com.demo.domain.Discount;
import com.demo.web.vm.DiscountVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api")
public interface DiscountResource {
    @Operation(
            summary = "Add discount",
            description = "Add new discount",
            tags = "discount",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Add discount successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT", content = @Content),
            @ApiResponse(responseCode = "409", description = "Discount name is already used", content = @Content)
    }
    )
    @PostMapping("/discount")
    ResponseEntity<Discount> createDiscount(@RequestBody @Valid DiscountVM discountVM);
}
