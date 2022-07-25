package com.example.dele_fashion_home.controller;

import com.example.dele_fashion_home.dto.CategoryDto;
import com.example.dele_fashion_home.model.CategoryEntity;
import com.example.dele_fashion_home.model.pagination.CategoryPagination;
import com.example.dele_fashion_home.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categoryposts")
public class CategoryController {
    private  final CategoryService categoryService;

    @PostMapping("/categoryposts")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/viewAParticularCategory/{id}")
    public ResponseEntity<CategoryEntity> viewACategory(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.fetchCategory(categoryId));
    }

    @GetMapping("/viewAllCategories")
    public  ResponseEntity<Page<CategoryEntity>> viewAllCategories(CategoryPagination categoryPagination){
        return ResponseEntity.ok(categoryService.viewCategories(categoryPagination));
    }

    @PatchMapping("/modifyCategory/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }


    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategpry(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
