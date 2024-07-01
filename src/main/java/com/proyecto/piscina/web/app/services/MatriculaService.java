package com.proyecto.piscina.web.app.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.respository.*;

@Service
public class MatriculaService {
	 @Autowired
	    private MatriculaRepository matriculaRepository;

	    @Autowired
	    private AlumnoRepository alumnoRepository;

	    @Autowired
	    private ClaseRepository claseRepository;

	    public List<Matricula> findAll() {
	        return matriculaRepository.findAll();
	    }

	    public Matricula findById(Long id) {
	        return matriculaRepository.findById(id)
	                .orElseThrow(() -> new IllegalStateException("Matrícula no encontrada con ID: " + id));
	    }

	    public Matricula saveMatricula(Matricula matricula) {
	        // Verificar si existe el Alumno
	        Alumno alumno = alumnoRepository.findById(matricula.getAlumno().getIdAlumno())
	                .orElseThrow(() -> new IllegalStateException("Alumno no encontrado con ID: " + matricula.getAlumno().getIdAlumno()));

	        // Verificar si existe la Clase
	        Clase clase = claseRepository.findById(matricula.getClase().getId())
	                .orElseThrow(() -> new IllegalStateException("Clase no encontrada con ID: " + matricula.getClase().getId()));

	        // Asignar Alumno y Clase validados
	        matricula.setAlumno(alumno);
	        matricula.setClase(clase);

	        // Establecer fecha actual si no se proporciona
	        if (matricula.getFechaMatricula() == null) {
	            matricula.setFechaMatricula(new Date());
	        }

	        // Guardar la Matricula
	        return matriculaRepository.save(matricula);
	    }

	    public void deleteById(Long id) {
	        boolean existe = matriculaRepository.existsById(id);
	        if (!existe) {
	            throw new IllegalStateException("Matrícula no encontrada con ID: " + id);
	        }
	        matriculaRepository.deleteById(id);
	    }

	    public Matricula update(Long id, Matricula updatedMatricula) {
	        Matricula existingMatricula = findById(id);

	        existingMatricula.setAlumno(updatedMatricula.getAlumno());
	        existingMatricula.setClase(updatedMatricula.getClase());
	        existingMatricula.setFechaMatricula(updatedMatricula.getFechaMatricula());
	        existingMatricula.setEstado(updatedMatricula.getEstado());

	        return matriculaRepository.save(existingMatricula);
	    }
	
	
}
