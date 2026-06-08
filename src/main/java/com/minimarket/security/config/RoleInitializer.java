package com.minimarket.security.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.minimarket.entity.Rol;
import com.minimarket.repository.RolRepository;

@Configuration
public class RoleInitializer {
     @Bean
    CommandLineRunner initRoles(RolRepository rolRepository) {
        return args -> {

            if (!rolRepository.existsByNombre("ROLE_CLIENTE")) {
                Rol rol = new Rol();
                rol.setNombre("ROLE_CLIENTE");
                rolRepository.save(rol);
            }

            if (!rolRepository.existsByNombre("ROLE_EMPLEADO")) {
                Rol rol = new Rol();
                rol.setNombre("ROLE_EMPLEADO");
                rolRepository.save(rol);
            }

            if (!rolRepository.existsByNombre("ROLE_ADMIN")) {
                Rol rol = new Rol();
                rol.setNombre("ROLE_ADMIN");
                rolRepository.save(rol);
            }
        };
    }
}
