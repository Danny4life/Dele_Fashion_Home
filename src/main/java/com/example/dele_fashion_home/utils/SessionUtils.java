package com.example.dele_fashion_home.utils;

import com.example.dele_fashion_home.exceptions.UserNotFoundException;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionUtils {
    private final HttpSession httpSession;

    private final UsersRepository userRepository;

    public Long getLoggedInUser(){
        Long userId = (Long) httpSession.getAttribute("user_id");
        if(userId == null) {
            throw new UserNotFoundException("You are not yet logged in!");
        }
        return userId;
    }

    public UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User not found!"));
    }
}
