package com.example.dele_fashion_home.service;

import com.example.dele_fashion_home.dto.LoginDto;
import com.example.dele_fashion_home.dto.SignUpDto;
import com.example.dele_fashion_home.model.UserEntity;

public interface UserService {
    UserEntity signUp(SignUpDto signUpDto);

    UserEntity login(LoginDto loginDto);

    UserEntity findUserById(Long id);

    String logout();

}
