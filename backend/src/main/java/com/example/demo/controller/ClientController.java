package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(clientService.login(loginRequest));
    }

    @GetMapping("/isLoggedIn")
    public ResponseEntity<Boolean> isLoggedIn() {
        return ResponseEntity.ok(clientService.isLoggedIn());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        clientService.logout();
        return ResponseEntity.ok().build();
    }
} 