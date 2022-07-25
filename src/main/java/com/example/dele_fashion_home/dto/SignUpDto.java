package com.example.dele_fashion_home.dto;

import com.example.dele_fashion_home.enums.UserRole;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {

    private String email;
    private String password;
    private String userName;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
