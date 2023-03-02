package com.demo.service;

import com.demo.domain.Product;
import com.demo.exception.ResourceAlreadyExistsException;
import com.demo.repository.ProductRepository;
import com.demo.service.dto.ProductDto;
import com.demo.service.mapper.ProductMapper;
import com.demo.web.vm.ProductVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductDto addProduct(ProductVM productVM) {
        if (productRepository.existsBySku(productVM.sku())) {
            throw new ResourceAlreadyExistsException("SKU " + productVM.sku() + " already existed");
        }
        Product product = productMapper.toEntity(productVM, categoryService);
        return productMapper.toDto(productRepository.save(product));
    }
}
