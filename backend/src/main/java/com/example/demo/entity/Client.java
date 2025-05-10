package com.example.demo.entity;

import com.example.demo.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public boolean isClient() {
        return Role.CLIENT.equals(this.role);
    }

    public boolean isBarista() {
        return Role.BARISTA.equals(this.role);
    }
} 