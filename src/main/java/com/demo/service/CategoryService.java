package com.demo.service;

import com.demo.domain.Category;
import com.demo.exception.ResourceAlreadyExistsException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ResourceAlreadyExistsException("Category with name " + name + " already existed");
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }
    public Category getCategoryByName(String name) {
        return categoryRepository
                .findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category with name " + name + " not found"));
    }
}
