package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private Role role;
} 