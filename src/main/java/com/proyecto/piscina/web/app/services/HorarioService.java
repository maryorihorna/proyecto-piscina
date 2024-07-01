package com.proyecto.piscina.web.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.respository.*;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
	private final HorarioRepository horarioRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public HorarioService(HorarioRepository horarioRepository, InstructorRepository instructorRepository) {
        this.horarioRepository = horarioRepository;
        this.instructorRepository = instructorRepository;
    }
    
    public Horario saveHorario(Horario horario) {
        Long instructorId = horario.getInstructor().getIdInstructor();
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("El instructor con id " + instructorId + " no existe"));
        horario.setInstructor(instructor);  // Asigna el objeto completo de Instructor
        return horarioRepository.save(horario);
    }

    public Horario updateHorario(Long id, Horario horarioDetails) {
        Horario horarioActual = horarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("El horario con id " + id + " no existe"));

        Long instructorId = horarioDetails.getInstructor().getIdInstructor();
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalStateException("Instructor no encontrado con id: " + instructorId));
        
        // Actualiza los campos del horario con los detalles proporcionados
        horarioActual.setInstructor(instructor);
        horarioActual.setDia_semana(horarioDetails.getDia_semana());
        horarioActual.setHora_inicio(horarioDetails.getHora_inicio());
        horarioActual.setHora_fin(horarioDetails.getHora_fin());
        
        return horarioRepository.save(horarioActual);
    }

    public Horario getHorario(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public List<Horario> getAllHorarios() {
        return horarioRepository.findAll();
    }

    public void deleteHorario(Long id) {
        boolean existe = horarioRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("El horario con id " + id + " no existe");
        }
        horarioRepository.deleteById(id);
    }

    public Optional<Horario> getHorario(long id) {
        return horarioRepository.findById(id);
    }
}
