package com.commerce.cashregister.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue()
    private UUID id;
    private String username;
    private char[] password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String username, char[] password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Add default constructor and other necessary methods
}
