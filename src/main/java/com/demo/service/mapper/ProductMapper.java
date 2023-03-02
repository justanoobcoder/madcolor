package com.demo.service.mapper;

import com.demo.domain.Product;
import com.demo.service.CategoryService;
import com.demo.service.dto.ProductDto;
import com.demo.web.vm.ProductVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "discount", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category", expression = "java(categoryService.getCategoryById(productVM.categoryId()))")
    Product toEntity(ProductVM productVM, CategoryService categoryService);

    @Mapping(target = "category", expression = "java(product.getCategory().getName())")
    @Mapping(target = "discountPercent", expression = "java(product.getDiscount() == null ? 0 : product.getDiscount().getPercent())")
    ProductDto toDto(Product product);
}
