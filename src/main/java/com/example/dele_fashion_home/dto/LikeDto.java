package com.example.dele_fashion_home.dto;

import com.example.dele_fashion_home.model.PostEntity;
import com.example.dele_fashion_home.model.UserEntity;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikeDto implements Serializable {
    private PostEntity posts;
    private UserEntity user;
}
