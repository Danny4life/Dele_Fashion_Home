package com.example.dele_fashion_home.service;

import com.example.dele_fashion_home.dto.CategoryDto;
import com.example.dele_fashion_home.model.CategoryEntity;
import com.example.dele_fashion_home.model.pagination.CategoryPagination;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryEntity createCategory(CategoryEntity category);

    Page<CategoryEntity> viewCategories(CategoryPagination categoryPagination);

    CategoryEntity updateCategory(Long categoryId, CategoryDto categoryDto);

    void deleteCategory(Long categoryId);

    CategoryEntity fetchCategory(Long categoryId);
}
