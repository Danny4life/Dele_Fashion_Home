package com.example.dele_fashion_home.repository;

import com.example.dele_fashion_home.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByCategoryName(String category);
}
