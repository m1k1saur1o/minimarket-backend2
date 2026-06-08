package com.minimarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minimarket.dto.RegistroRequest;
import com.minimarket.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody RegistroRequest request) {
        return authService.authenticateUser(request);
    }

    @PostMapping("/registrar")
    public String registerUser(@RequestBody RegistroRequest request) {
        return authService.registerUser(request);
    }
}