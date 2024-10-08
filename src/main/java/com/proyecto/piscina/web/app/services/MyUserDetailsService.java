package com.proyecto.piscina.web.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + username);
        }
        return new UsuarioPrincipal(usuario);
    }
}