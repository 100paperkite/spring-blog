package com.example.blog.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final String adminId;
    private final String adminPassword;

    public AuthService(@Value("${blog.admin.id}") String adminId, @Value("${blog.admin.password}") String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    public boolean isAdmin(String id, String password) {
        return id.equals(adminId) && password.equals(adminPassword);
    }
}
