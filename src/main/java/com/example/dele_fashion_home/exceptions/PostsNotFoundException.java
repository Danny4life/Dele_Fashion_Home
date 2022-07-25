package com.example.dele_fashion_home.exceptions;

public class PostsNotFoundException extends RuntimeException{
    public PostsNotFoundException(String message) {
        super(message);
    }
}
