package com.proyecto.piscina.web.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.respository.*;

@Service
public class ClaseService {
	@Autowired
    private ClaseRepository claseRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private InstructorRepository instructorRepository;

    public List<Clase> findAll() {
        return claseRepository.findAll();
    }

    public Clase findById(Long id) {
        return claseRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("Clase no encontrada con ID: " + id));
    }

    public Clase saveClase(Clase clase) {
        // Verificar si existe el Curso
        Curso curso = cursoRepository.findById(clase.getCurso().getId_curso())
                .orElseThrow(() -> new IllegalStateException("Curso no encontrado con ID: " + clase.getCurso().getId_curso()));

        // Verificar si existe el Instructor
        Instructor instructor = instructorRepository.findById(clase.getInstructor().getIdInstructor())
                .orElseThrow(() -> new IllegalStateException("Instructor no encontrado con ID: " + clase.getInstructor().getIdInstructor()));

        // Asignar Curso e Instructor validados
        clase.setCurso(curso);
        clase.setInstructor(instructor);

        // Guardar la Clase
        return claseRepository.save(clase);
    }

    public void deleteById(Long id) {
        boolean existe = claseRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("Clase no encontrada con ID: " + id);
        }
        claseRepository.deleteById(id);
    }

    public Clase update(Long id, Clase updatedClase) {
        Clase existingClase = findById(id);
        existingClase.setCurso(updatedClase.getCurso());
        existingClase.setInstructor(updatedClase.getInstructor());
        existingClase.setFecha(updatedClase.getFecha());
        existingClase.setHoraInicio(updatedClase.getHoraInicio());
        existingClase.setHoraFin(updatedClase.getHoraFin());
        return saveClase(existingClase);
    }
}
