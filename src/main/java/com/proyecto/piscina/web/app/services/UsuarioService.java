package com.proyecto.piscina.web.app.services;

import java.util.Set;
import org.springframework.stereotype.Service;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Usuario saveUsuario(Usuario usuario) {
        // Asignar el rol USER por defecto
        Set<String> roles = usuario.getRoles();
        if (roles == null || roles.isEmpty()) {
            usuario.setRoles(Collections.singleton("USER")); // Asignar el rol USER por defecto
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(long id, Usuario usuario) {
        Usuario usuarioActual = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("El usuario con id " + id + " no existe"));
        usuarioActual.setUsername(usuario.getUsername());
        usuarioActual.setPassword(usuario.getPassword());
        return usuarioRepository.save(usuarioActual);
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void deleteUsuario(Long id) {
        boolean existe = usuarioRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("El usuario con id " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getUsuario(long id) {
        return usuarioRepository.findById(id);
    }
}