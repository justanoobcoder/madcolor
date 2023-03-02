package com.demo.web;

import com.demo.service.ProductService;
import com.demo.service.dto.ProductDto;
import com.demo.web.api.ProductResource;
import com.demo.web.vm.ProductVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductResourceImpl implements ProductResource {
    private final ProductService productService;
    @Override
    public ResponseEntity<ProductDto> createProduct(ProductVM productVM) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(productVM));
    }
}
