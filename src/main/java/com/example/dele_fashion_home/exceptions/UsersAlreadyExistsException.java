package com.example.dele_fashion_home.exceptions;

public class UsersAlreadyExistsException extends RuntimeException{
    public UsersAlreadyExistsException(String message) {
        super(message);
    }

}
