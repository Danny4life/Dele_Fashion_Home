package com.example.dele_fashion_home.repository;

import com.example.dele_fashion_home.model.LikeEntity;
import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    @Query("SELECT COUNT(lp) > 0 FROM LikeEntity lp WHERE lp.user = :user AND lp.posts = :post")
    boolean likeEntityExistByUserAndPost(UserEntity user, PostEntity post);

    @Modifying
    @Query("DELETE FROM LikeEntity lp WHERE lp.user = :user AND lp.posts = :post")
    void deleteLikeEntityByUserAndPosts(UserEntity user, PostEntity post);
}
