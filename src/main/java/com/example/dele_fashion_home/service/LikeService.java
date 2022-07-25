package com.example.dele_fashion_home.service;

public interface LikeService {
    String likeAPost(Long postId);

    int totalNumberOfLikesPerPost(Long postId);

    String unlikePost(Long postId);
    //Long getUserLoggedIn();
}
