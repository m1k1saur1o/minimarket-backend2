package com.minimarket.service;

import java.util.List;
import java.util.Optional;

import com.minimarket.entity.Rol;

public interface RolService {
    Optional<Rol> findByNombre(String nombre);
    List<Rol> findAll();
}
