package com.example.demo.service;

import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponse signup(SignupRequest signupRequest) {
        try {
            logger.info("Processing signup request for email: {}", signupRequest.getEmail());

            // Validate request
            if (signupRequest.getEmail() == null || signupRequest.getPassword() == null || signupRequest.getRole() == null) {
                throw new IllegalArgumentException("Email, password, and role are required");
            }

            // Check if email already exists
            if (clientRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
                logger.warn("Email already registered: {}", signupRequest.getEmail());
                throw new RuntimeException("Email already registered");
            }

            // Create new client
            Client client = new Client();
            client.setEmail(signupRequest.getEmail());
            client.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            client.setRole(signupRequest.getRole());
            client.setStatus(true);

            // Save the client
            client = clientRepository.save(client);
            logger.info("Successfully created new client with email: {}", client.getEmail());

            // Create and return login response
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setEmail(client.getEmail());
            response.setStatus(client.isStatus());
            response.setRole(client.getRole());

            return response;
        } catch (Exception e) {
            logger.error("Error during signup process: ", e);
            throw new RuntimeException("Error during signup: " + e.getMessage());
        }
    }
} 