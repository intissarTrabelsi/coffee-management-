package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Client client = clientRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        LoginResponse response = new LoginResponse();
        
        if (client != null && passwordEncoder.matches(loginRequest.getPassword(), client.getPassword())) {
            response.setSuccess(true);
            response.setEmail(client.getEmail());
            response.setStatus(client.isStatus());
            response.setRole(client.getRole());
        } else {
            response.setSuccess(false);
        }
        
        return response;
    }

    public boolean isLoggedIn() {
        // Implement your session management logic here
        // This is a placeholder implementation
        return false;
    }

    public void logout() {
        // Implement your logout logic here
        // This is a placeholder implementation
    }
} 