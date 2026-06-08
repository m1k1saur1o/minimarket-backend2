package com.minimarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minimarket.entity.Rol;
import com.minimarket.service.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    
    @Autowired
    private RolService rolService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Rol> listarRoles() {
        return rolService.findAll();
    }

}
