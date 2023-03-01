package com.demo.web.api;

import com.demo.domain.Category;
import com.demo.web.vm.CategoryVM;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
public interface CategoryResource {
    @Operation(
            summary = "Add new category",
            description = "Add new category",
            tags = "category",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Add category successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category name is already used", content = @Content)
    }
    )
    @PostMapping("/category")
    ResponseEntity<Category> addNewCategory(@RequestBody @Valid CategoryVM categoryVM);

    @Operation(
            summary = "Get category by name",
            description = "Get category by name",
            tags = "category",
            security = @SecurityRequirement(name = "token_auth")
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Get category successfully"),
            @ApiResponse(responseCode = "401", description = "Missing or invalid JWT", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    }
    )
    @GetMapping("/category")
    ResponseEntity<Category> getCategoryByName(@RequestParam String name);
}
