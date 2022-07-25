package com.example.dele_fashion_home.controller;

import com.example.dele_fashion_home.dto.LoginDto;
import com.example.dele_fashion_home.dto.SignUpDto;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign_up")
    public ResponseEntity<UserEntity> userSignUp(@RequestBody SignUpDto signUpDto){
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> userLogin(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @GetMapping("/log-out")
    public ResponseEntity<String> logout(){
        return ResponseEntity.ok(userService.logout());
    }
}
