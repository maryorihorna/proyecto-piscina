package com.proyecto.piscina.web.app.services;

import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.respository.InstructorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor saveInstructor(Instructor instructor) {
        
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(long id, Instructor instructor) {
        Instructor instructorActual = instructorRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("El instructor con id " + id + " no existe"));
            instructorActual.setNombre(instructor.getNombre());
            instructorActual.setApellido(instructor.getApellido());
            instructorActual.setTelefono(instructor.getTelefono());
            instructorActual.setEmail(instructor.getEmail());
        return instructorRepository.save(instructorActual);
    }

    public Instructor getInstructor(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public void deleteInstructor(Long id) {
        boolean existe = instructorRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("El instructor con id " + id + " no existe");
        }
        instructorRepository.deleteById(id);
    }

    public Optional<Instructor> getInstructor(long id) {
        return instructorRepository.findById(id);
    }
}