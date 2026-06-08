package com.minimarket.service;

import com.minimarket.dto.RegistroRequest;

public interface AuthService {
    String authenticateUser(RegistroRequest request);
    String registerUser(RegistroRequest request);
}
