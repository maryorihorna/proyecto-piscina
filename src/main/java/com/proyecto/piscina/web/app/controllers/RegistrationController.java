package com.proyecto.piscina.web.app.controllers;

import org.slf4j.Logger; // Add this import statement
import org.slf4j.LoggerFactory; // Add this import statement

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;
import com.proyecto.piscina.web.app.services.UsuarioService;

@Controller
public class RegistrationController {

 

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario) {
        // Verifica que el nombre de usuario no esté vacío o ya exista
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty() || usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            return "register";
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.saveUsuario(usuario);
        return "registrationSuccess";
    }
}