package com.proyecto.piscina.web.app.services;

import org.springframework.stereotype.Service;
import com.proyecto.piscina.web.app.entities.Administrador;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.AdministradorRespository;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    private final AdministradorRespository administradorRepository;

    private final UsuarioRepository usuarioRepository;
    
    public AdministradorService(AdministradorRespository administradorRepository, UsuarioRepository usuarioRepository) {
        this.administradorRepository = administradorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Administrador saveAdministrador(Administrador administrador) {
        // Primero, guarda el usuario
        Usuario savedUsuario = usuarioRepository.save(administrador.getUsuario());
        // Establece el usuario guardado en el alumno
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