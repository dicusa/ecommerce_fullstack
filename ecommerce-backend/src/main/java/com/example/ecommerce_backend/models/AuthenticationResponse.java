package com.example.ecommerce_backend.models;


import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String token;

    // Default constructor
    public AuthenticationResponse() {
    }

    // Parameterized constructor
    public AuthenticationResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
