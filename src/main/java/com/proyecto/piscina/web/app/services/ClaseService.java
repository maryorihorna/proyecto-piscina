package com.proyecto.piscina.web.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.respository.*;

@Service
public class ClaseService {
	private final ClaseRepository claseRepository;
    private final CursoRepository cursoRepository;
    private final InstructorRepository instructorRepository;    

    public ClaseService(ClaseRepository claseRepository, CursoRepository cursoRepository,
            InstructorRepository instructorRepository) {
        this.claseRepository = claseRepository;
        this.cursoRepository = cursoRepository;
        this.instructorRepository = instructorRepository;
    }

    public Clase saveClase(Clase clase) {
        Long cursoId = clase.getCurso().getId_curso();
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new IllegalArgumentException("El curso con id " + cursoId + " no existe"));
        Long instructorId = clase.getInstructor().getIdInstructor();
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new IllegalArgumentException("El instructor con id " + instructorId + " no existe"));
        clase.setCurso(curso);
        clase.setInstructor(instructor);
        return claseRepository.save(clase);
    }

    public Clase updateClase(Long id, Clase claseDetails) {
        Clase claseActual = claseRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("La clase con id " + id + " no existe"));
        
        Long cursoId = claseDetails.getCurso().getId_curso();
        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new IllegalStateException("Curso no encontrado con id: " + cursoId ));
        Long instructorId = claseDetails.getInstructor().getIdInstructor();
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new IllegalStateException("Instructor no encontrado con id: " + instructorId));
        
        claseActual.setCurso(curso);
        claseActual.setInstructor(instructor);
        claseActual.setFecha(claseDetails.getFecha());
        claseActual.setHoraInicio(claseDetails.getHoraInicio());
        claseActual.setHoraFin(claseDetails.getHoraFin());
        
        return claseRepository.save(claseActual);
    }

    public Clase getClase(Long id) {
        return claseRepository.findById(id).orElse(null);
    }

    public List<Clase> getAllClases() {
        return claseRepository.findAll();
    }

    public void deleteClase(Long id) {
        boolean existe = claseRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("La clase con id " + id + " no existe");
        }
        claseRepository.deleteById(id);
    }

    public Optional<Clase> getClase(long id) {
        return claseRepository.findById(id);
    }
    
}
