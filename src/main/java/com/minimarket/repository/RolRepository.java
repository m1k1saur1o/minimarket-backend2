package com.minimarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minimarket.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
