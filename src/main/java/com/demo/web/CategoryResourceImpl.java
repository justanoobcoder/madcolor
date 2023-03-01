package com.demo.web;

import com.demo.domain.Category;
import com.demo.service.CategoryService;
import com.demo.web.api.CategoryResource;
import com.demo.web.vm.CategoryVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryResourceImpl implements CategoryResource {
    private final CategoryService categoryService;
    @Override
    public ResponseEntity<Category> addNewCategory(CategoryVM categoryVM) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.addCategory(categoryVM.name()));
    }

    @Override
    public ResponseEntity<Category> getCategoryBYName(String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getCategoryByName(name));
    }
}
