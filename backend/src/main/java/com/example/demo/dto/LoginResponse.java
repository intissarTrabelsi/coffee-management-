package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class LoginResponse {
    private boolean success;
    private String email;
    private boolean status;
    private Role role;
} 