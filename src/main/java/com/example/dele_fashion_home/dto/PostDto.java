package com.example.dele_fashion_home.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String title;
    private String description;
    private int likes;
    private int comments;
}
