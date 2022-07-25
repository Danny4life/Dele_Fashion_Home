package com.example.dele_fashion_home.service;

import com.example.dele_fashion_home.dto.CommentDto;
import com.example.dele_fashion_home.model.CommentEntity;

import java.util.List;

public interface CommentService {
    void makeComment(Long postId, CommentEntity commentEntity);
    //Long getUserLoggedIn();

    int totalNumberOfComments(Long postId);


    CommentEntity updateComment(Long commentId, CommentDto commentDto);

    String deleteComment(Long commentId);

    CommentEntity viewCommentById(Long postId);

    List<CommentDto> viewAllcommentByPost(Long postId);
}
