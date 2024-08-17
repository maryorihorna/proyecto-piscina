package com.proyecto.piscina.web.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

@Service
public class ProfileService {
@Autowired
    private UsuarioRepository usuarioRepository;

    public Alumno getAlumnoByUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Usuario usuario = usuarioRepository.findByUsername(username);
        return usuario.getAlumno();
    }
}
