package com.proyecto.piscina.web.app.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.AlumnoRepository;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;

@Service
public class AlumnoService {
    private final AlumnoRepository alumnoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AlumnoService(AlumnoRepository alumnoRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.alumnoRepository = alumnoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Alumno saveAlumno(Alumno alumno) {
        // Encriptar la contraseña del usuario
        Usuario usuario = alumno.getUsuario();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar el rol USER al usuario
        usuario.setRoles(Collections.singleton("USER"));

        // Primero, guarda el usuario
        Usuario savedUsuario = usuarioRepository.save(usuario);

        // Establece el usuario guardado en el alumno
        alumno.setUsuario(savedUsuario);

        return alumnoRepository.save(alumno);
    }

    public Alumno updateAlumno(long id, Alumno alumno) {
        Alumno alumnoactual = alumnoRepository.findById(id).orElseThrow(() -> new IllegalStateException("El cliente con id " + id + " no existe"));
        alumnoactual.setNombre(alumno.getNombre());
        alumnoactual.setApellido(alumno.getApellido());
        alumnoactual.setTelefono(alumno.getTelefono());
        alumnoactual.setEmail(alumno.getEmail());
        alumnoactual.setDireccion(alumno.getDireccion());
        alumnoactual.setFecha_nacimiento(alumno.getFecha_nacimiento());
        return alumnoRepository.save(alumnoactual);
    }

    public Alumno getAlumno(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    public void deleteAlumno(Long id) {
        boolean existe = alumnoRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("El alumno con id " + id + " no existe");
        }
        alumnoRepository.deleteById(id);
    }

    public Optional<Alumno> getAlumno(long id) {
        return alumnoRepository.findById(id);
    }

    public long contarAlumnos() {
        return alumnoRepository.count();
    }
}