package com.example.dele_fashion_home.controller;

import com.example.dele_fashion_home.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users_likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/like_a_post/{id}")
    public ResponseEntity<String> likeAPost(@PathVariable("id") Long postId){
        return ResponseEntity.ok(likeService.likeAPost(postId));
    }

    @GetMapping("/noOfLikes/{id}")
    public int totalNumberOfLikes(@PathVariable ("id") Long postId){
        return likeService.totalNumberOfLikesPerPost(postId);
    }

    @DeleteMapping("/unlike-post/{id}")
    public ResponseEntity<String> unlikePost(@PathVariable ("id") Long postId){
        return ResponseEntity.ok(likeService.unlikePost(postId));
    }
}
