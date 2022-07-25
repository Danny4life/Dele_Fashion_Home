package com.example.dele_fashion_home.service;

import com.example.dele_fashion_home.dto.PostDto;
import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.pagination.PostPagination;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto, Long categoryId);

    Page<PostEntity> viewAllPosts(PostPagination postPagination);

    List<PostDto> viewAllPostsByCategory(Long categoryId);


    PostEntity viewPostById(Long postId);

    PostEntity updatePost(Long postId, PostDto postDto);

    void deletePost(Long postId);
}
