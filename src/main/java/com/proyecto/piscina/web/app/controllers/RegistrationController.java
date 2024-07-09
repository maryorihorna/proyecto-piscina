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

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute Usuario usuario) {
        // Verifica que el nombre de usuario no esté vacío o ya exista
        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            logger.error("El nombre de usuario está vacío");
            return "register";
        }
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            logger.error("El nombre de usuario ya existe: " + usuario.getUsername());
            return "register";
        }

        try {
            // Guarda el usuario con la contraseña encriptada
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            Usuario usuarioGuardado = usuarioService.saveUsuario(usuario);
            
            // Verifica si el usuario se guardó correctamente
            if (usuarioGuardado != null && usuarioGuardado.getIdUsuario() != null) {
                logger.info("Usuario guardado con éxito: " + usuarioGuardado.getUsername());
            } else {
                logger.error("El usuario no se pudo guardar.");
            }
        } catch (Exception e) {
            logger.error("Error al guardar el usuario: " + e.getMessage());
        }
        logger.info("Usuario registrado: " + usuario.getUsername());
        return "registrationSuccess"; // Assuming "registrationSuccess" is the view name for successful registration
    }
}