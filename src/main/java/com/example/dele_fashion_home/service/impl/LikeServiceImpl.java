package com.example.dele_fashion_home.service.impl;

import com.example.dele_fashion_home.exceptions.AlreadyLikedException;
import com.example.dele_fashion_home.exceptions.PostsNotFoundException;
import com.example.dele_fashion_home.exceptions.UserNotFoundException;
import com.example.dele_fashion_home.model.LikeEntity;
import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.repository.LikeRepository;
import com.example.dele_fashion_home.repository.PostRepository;
import com.example.dele_fashion_home.service.LikeService;
import com.example.dele_fashion_home.service.UserService;
import com.example.dele_fashion_home.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final HttpSession httpSession;
    private final UserService userService;
    private final PostRepository postRepository;

    private  final SessionUtils sessionUtils;

    @Override
    public String  likeAPost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        if(likeRepository.likeEntityExistByUserAndPost(user, postEntity))
            throw new AlreadyLikedException("You have liked this post before");

        LikeEntity likeEntity = LikeEntity.builder()
                .posts(postEntity)
                .user(user)
                .timeLiked(LocalDateTime.now())
                .build();


        postEntity.getLikedItems().add(likeEntity);
        postRepository.save(postEntity);

        return "One like";
    }

    @Override
    public int totalNumberOfLikesPerPost(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(()-> new PostsNotFoundException("post not found"));

        return post.getLikedItems().size();
    }

    @Override
    @Transactional
    public String unlikePost(Long postId) {
        Long id = sessionUtils.getLoggedInUser();
        UserEntity user = sessionUtils.findUserById(id);

        if(user == null) throw new UserNotFoundException("You are not logged in");

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PostsNotFoundException("This post is not available"));

        likeRepository.deleteLikeEntityByUserAndPosts(user, postEntity);
        return "Post unliked";
    }
}
