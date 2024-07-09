package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piscina.web.app.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}