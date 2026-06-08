package com.minimarket.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minimarket.dto.RegistroRequest;
import com.minimarket.entity.Rol;
import com.minimarket.entity.Usuario;
import com.minimarket.repository.RolRepository;
import com.minimarket.repository.UsuarioRepository;
import com.minimarket.security.util.JwtUtil;
import com.minimarket.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil utils;

    @Override
    public String authenticateUser(RegistroRequest request) {
        Authentication authentication = authManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return utils.generateToken(userDetails.getUsername());
    }

    @Override
    public String registerUser(RegistroRequest request) {
        if (usuarioRepo.existsByUsername(request.getUsername())) {
            return "User already exists!";
        }

        Rol rol = rolRepo
            .findByNombre("ROLE_" + request.getRole().toUpperCase())
            .orElse(null);
        
        if (rol == null) {
            return "Role does not exist!";
        }

        final Usuario newUser = new Usuario(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword())
        );
        
        newUser.setRoles(Set.of(rol));
        usuarioRepo.save(newUser);
        return "User registered successfully!";
    }
}   
