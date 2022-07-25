package com.example.dele_fashion_home.service.impl;

import com.example.dele_fashion_home.dto.LoginDto;
import com.example.dele_fashion_home.dto.SignUpDto;
import com.example.dele_fashion_home.exceptions.PasswordIncorrectException;
import com.example.dele_fashion_home.exceptions.UserNotFoundException;
import com.example.dele_fashion_home.exceptions.UsersAlreadyExistsException;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.repository.UsersRepository;
import com.example.dele_fashion_home.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final HttpSession httpSession;
    @Override
    public UserEntity signUp(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();
        if(usersRepository.existsByEmail(email)){
            throw new UsersAlreadyExistsException("Users with " + email+ "already exists");
        }
        UserEntity users = new UserEntity();
        BeanUtils.copyProperties(signUpDto, users);

        return usersRepository.save(users);
    }

    @Override
    public UserEntity login(LoginDto loginDto) {
        String userEmail = loginDto.getEmail();
        String userPassword = loginDto.getPassword();

        UserEntity userEntity = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(!userEntity.getPassword().equals(userPassword)){
            throw new PasswordIncorrectException("Password Incorrect");
        }

        httpSession.setAttribute("user_id", userEntity.getUserId());
        httpSession.setAttribute("Permission", "UserEntity");
        return userEntity;
    }

    @Override
    public UserEntity findUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() ->new UserNotFoundException("User not found"));
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "User Logged Out" ;
    }

}
