package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piscina.web.app.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // @Query("SELECT u FROM usuarios u WHERE u.username = :username")
    // public Usuario getUserByUsername(@Param("username") String username);
    Usuario findByUsername(String username);
}