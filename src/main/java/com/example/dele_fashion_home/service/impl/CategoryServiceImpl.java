package com.example.dele_fashion_home.service.impl;

import com.example.dele_fashion_home.dto.CategoryDto;
import com.example.dele_fashion_home.enums.UserRole;
import com.example.dele_fashion_home.exceptions.CategoryAlreadyExistException;
import com.example.dele_fashion_home.exceptions.CategoryNotFoundException;
import com.example.dele_fashion_home.exceptions.EmptyListException;
import com.example.dele_fashion_home.exceptions.PermissionDeniedException;
import com.example.dele_fashion_home.model.CategoryEntity;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.model.pagination.CategoryPagination;
import com.example.dele_fashion_home.repository.CategoryRepository;
import com.example.dele_fashion_home.service.CategoryService;
import com.example.dele_fashion_home.service.UserService;
import com.example.dele_fashion_home.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final HttpSession httpSession;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    private final SessionUtils sessionUtils;




    @Override
    public CategoryEntity createCategory(CategoryEntity category) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        if(categoryRepository.existsByCategoryName(category.getCategoryName()))
            throw new CategoryAlreadyExistException("This category already exists");

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryName(category.getCategoryName())
                .build();
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Page<CategoryEntity> viewCategories(CategoryPagination categoryPagination) {
        Sort sort = Sort.by(categoryPagination.getSortDirection(), categoryPagination.getSortBy());
        Pageable pageable = PageRequest.of(categoryPagination.getPageNumber(), categoryPagination.getPageSize(), sort);
        Page<CategoryEntity> allCategories = categoryRepository.findAll(pageable);
        if(allCategories.isEmpty()) throw new EmptyListException("You have not created any category");
        return allCategories;
    }

    @Override
    public CategoryEntity updateCategory(Long categoryId, CategoryDto categoryDto) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        if(Objects.nonNull(categoryDto.getCategoryName()) && !"".equalsIgnoreCase(categoryDto.getCategoryName()))
            BeanUtils.copyProperties(categoryDto, category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        categoryRepository.delete(category);
    }

    @Override
    public CategoryEntity fetchCategory(Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if (!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("Only admins can upload post");

        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
            return category.get();
        throw new CategoryNotFoundException("This category is not available");
    }

//
}
