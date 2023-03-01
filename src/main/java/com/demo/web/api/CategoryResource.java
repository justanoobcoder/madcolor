package com.demo.web.api;

import com.demo.domain.Category;
import com.demo.web.vm.CategoryVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
public interface CategoryResource {
    @Operation(
            security = @SecurityRequirement(name = "token_auth")
    )
    @PostMapping("/category")
    ResponseEntity<Category> addNewCategory(@RequestBody @Valid CategoryVM categoryVM);

    @Operation(
            security = @SecurityRequirement(name = "token_auth")
    )
    @GetMapping("/category")
    ResponseEntity<Category> getCategoryBYName(@RequestParam String name);
}
