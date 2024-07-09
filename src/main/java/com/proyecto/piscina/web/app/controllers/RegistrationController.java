package com.proyecto.piscina.web.app.controllers;

import org.slf4j.Logger; // Add this import statement
import org.slf4j.LoggerFactory; // Add this import statement

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

@Controller
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
   
    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario, Model model) {
        // Verifica que el nombre de usuario no esté vacío o ya exista
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            logger.error("El nombre de usuario está vacío");
            model.addAttribute("error", "El nombre de usuario está vacío");
            return "register";
        }
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            logger.error("El nombre de usuario ya existe: " + usuario.getUsername());
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "register";
        }

        // Guarda el usuario con la contraseña encriptada
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        logger.info("Usuario registrado: " + usuario.getUsername());
        return "redirect:/login";
    }
}