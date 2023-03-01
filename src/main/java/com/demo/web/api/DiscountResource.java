package com.demo.web.api;

import com.demo.domain.Discount;
import com.demo.web.vm.DiscountVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api")
public interface DiscountResource {
    @Operation(
            tags = "discount",
            security = @SecurityRequirement(name = "token_auth")
    )
    @PostMapping("/discount")
    ResponseEntity<Discount> createDiscount(@RequestBody @Valid DiscountVM discountVM);
}
