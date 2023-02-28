package com.demo.web.api;

import com.demo.service.dto.CustomerDto;
import com.demo.web.vm.CustomerVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
public interface CustomerResource {
    @Operation(
            security = @SecurityRequirement(name = "token_auth")
    )
    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto createNewCustomer(@RequestBody @Valid CustomerVM customerVM);

    @Operation(
            security = @SecurityRequirement(name = "token_auth")
    )
    @GetMapping(value = "/customers/{telephone}", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto getCustomer(@PathVariable String telephone);

    @Operation(
            security = @SecurityRequirement(name = "token_auth")
    )
    @PatchMapping(value = "/customers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerVM customerVM);
}
