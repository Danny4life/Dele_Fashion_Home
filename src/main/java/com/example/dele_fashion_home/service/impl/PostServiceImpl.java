package com.example.dele_fashion_home.service.impl;

import com.example.dele_fashion_home.dto.PostDto;
import com.example.dele_fashion_home.enums.UserRole;
import com.example.dele_fashion_home.exceptions.CategoryNotFoundException;
import com.example.dele_fashion_home.exceptions.EmptyListException;
import com.example.dele_fashion_home.exceptions.PermissionDeniedException;
import com.example.dele_fashion_home.exceptions.PostsNotFoundException;
import com.example.dele_fashion_home.model.CategoryEntity;
import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.model.pagination.PostPagination;
import com.example.dele_fashion_home.repository.CategoryRepository;
import com.example.dele_fashion_home.repository.CommentRepository;
import com.example.dele_fashion_home.repository.PostRepository;
import com.example.dele_fashion_home.service.PostService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final HttpSession httpSession;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final SessionUtils sessionUtils;



    @Override
    public void createPost(PostDto postDto, Long categoryId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));
        PostEntity post = PostEntity.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(category)
                .likedItems(new ArrayList<>())
                .comments(new ArrayList<>())
                .user(user)
                .build();
        postRepository.save(post);

        category.getPosts().add(post);
        categoryRepository.save(category);
    }

    @Override
    public Page<PostEntity> viewAllPosts(PostPagination postPagination) {
        Sort sort = Sort.by(postPagination.getSortDirection(), postPagination.getSortBy());
        Pageable pageable = PageRequest.of(postPagination.getPageNumber(), postPagination.getPageSize(), sort);
        Page<PostEntity> postEntityList = postRepository.findAll(pageable);
        if(postEntityList.isEmpty()) throw new EmptyListException("You have not created any post");
        return postEntityList;
    }

    @Override
    public List<PostDto> viewAllPostsByCategory(Long categoryId) {

        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("This category is not available"));

        List<PostEntity> posts = category.getPosts();
        List<PostDto> postDtos = new ArrayList<>();

        for(PostEntity postEntity : posts){
            PostDto userPostDto = new PostDto();
            BeanUtils.copyProperties(postEntity, userPostDto);

            postDtos.add(userPostDto);
        }

        return postDtos;
    }

    @Override
    public PostEntity viewPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
    }

    @Override
    public PostEntity updatePost(Long postId, PostDto postDto) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
        if(Objects.nonNull(postDto.getTitle()) && !"".equalsIgnoreCase(postDto.getTitle()))
            BeanUtils.copyProperties(postDto, postEntity);
        if(Objects.nonNull(postDto.getDescription()) && !"".equalsIgnoreCase(postDto.getDescription()))
            BeanUtils.copyProperties(postDto, postEntity);
        return postRepository.save(postEntity);
    }

    @Override
    public void deletePost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(!user.getUserRole().equals(UserRole.ADMIN))
            throw new PermissionDeniedException("You do not have the permission to access this page");

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));
        postRepository.delete(post);
    }


}
