package com.example.dele_fashion_home.controller;

import com.example.dele_fashion_home.dto.CommentDto;
import com.example.dele_fashion_home.dto.PostDto;
import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.pagination.PostPagination;
import com.example.dele_fashion_home.service.CommentService;
import com.example.dele_fashion_home.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/adminposts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/createPostBycategoryId/{id}")
    public String createNewPost(@PathVariable("id") Long categoryId, @RequestBody PostDto postDto){
        postService.createPost(postDto, categoryId);
        return "Post created";
    }

    @GetMapping("/viewAllPosts")
    public ResponseEntity<Page<PostEntity>> viewAllPosts(PostPagination postPagination){
        return ResponseEntity.ok(postService.viewAllPosts(postPagination));
    }

    @GetMapping("/viewPostById/{id}")
    public ResponseEntity<PostEntity> viewPostById(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(postService.viewPostById(postId));
    }

    @GetMapping("/viewPostByCategory/{id}")
    public ResponseEntity<List<PostDto>> viewAllPostByCategory(@PathVariable ("id") Long categoryId){
        return ResponseEntity.ok(postService.viewAllPostsByCategory(categoryId));
    }
    @GetMapping("/viewAllCommmentInAPost/{id}")
    public ResponseEntity<List<CommentDto>> viewAllCommentByPost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(commentService.viewAllcommentByPost(postId));
    }

    @PostMapping("/updatePost/{id}")
    public ResponseEntity<PostEntity> updatePost(@PathVariable ("id") Long postId, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @DeleteMapping("/deletepost/{id}")
    public String deletePost(@PathVariable ("id") Long postId){
        postService.deletePost(postId);
        return "Post deletion successful";
    }

}
