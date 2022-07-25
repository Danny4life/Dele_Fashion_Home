package com.example.dele_fashion_home.controller;

import com.example.dele_fashion_home.dto.CommentDto;
import com.example.dele_fashion_home.model.CommentEntity;
import com.example.dele_fashion_home.service.CommentService;
import com.example.dele_fashion_home.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users_comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/make_comment/{id}")
    public String makeCommentToAPost(@PathVariable("id") Long postId, @RequestBody CommentEntity commentEntity){
        commentService.makeComment(postId, commentEntity);
        return "Comment saved successfully";
    }

    @GetMapping("/number_of_comments/{id}")
    public int numberOfComments(@PathVariable ("id") Long postId){
        return commentService.totalNumberOfComments(postId);
    }


    @PatchMapping("/updateComment/{id}")
    public ResponseEntity<CommentEntity> updateComment(@PathVariable ("id") Long commentId, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(commentId, commentDto));
    }

    @DeleteMapping("/delete_comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable ("id") Long commentId){
        return  ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
