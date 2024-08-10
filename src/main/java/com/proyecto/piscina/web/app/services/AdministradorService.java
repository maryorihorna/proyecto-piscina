package com.proyecto.piscina.web.app.services;

import org.springframework.stereotype.Service;
import com.proyecto.piscina.web.app.entities.Administrador;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.AdministradorRespository;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    private final AdministradorRespository administradorRepository;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdministradorService(UsuarioRepository usuarioRepository, AdministradorRespository administradorRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.administradorRepository = administradorRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Administrador saveAdministrador(Administrador administrador) {
        // Encriptar la contraseña del usuario
        Usuario usuario = administrador.getUsuario();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Primero, guarda el usuario
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // Establece el usuario guardado en el administrador
        administrador.setUsuario(savedUsuario);
        return administradorRepository.save(administrador);
    }

    public Administrador updateAdministrador(long id, Administrador administrador) {
        Administrador administradorActual = administradorRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("El administrador con id " + id + " no existe"));
            administradorActual.setNombre(administrador.getNombre());
            administradorActual.setApellido(administrador.getApellido());
            administradorActual.setTelefono(administrador.getTelefono());
            administradorActual.setEmail(administrador.getEmail());
        return administradorRepository.save(administradorActual);
    }

    public Administrador getAdministrador(Long id) {
        return administradorRepository.findById(id).orElse(null);
    }

    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    public void deleteAdministrador(Long id) {
        boolean existe = administradorRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("El administrador con id " + id + " no existe");
        }
        administradorRepository.deleteById(id);
    }

    public Optional<Administrador> getAdministrador(long id) {
        return administradorRepository.findById(id);
    }
}