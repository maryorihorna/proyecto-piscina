package com.proyecto.piscina.web.app.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Horario;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.respository.HorarioRepository;
import com.proyecto.piscina.web.app.respository.InstructorRepository;

@Service
public class HorarioService {
	private final HorarioRepository horarioRepository;
    private final InstructorRepository instructorRepository;

    
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

public Map<String, Long> contarHorariosPorDia() {
    List<Horario> horarios = horarioRepository.findAll();

    // Mapa para definir el orden de los días de la semana
    Map<String, Integer> ordenDiasSemana = new LinkedHashMap<>();
    ordenDiasSemana.put("Lunes", 1);
    ordenDiasSemana.put("Martes", 2);
    ordenDiasSemana.put("Miércoles", 3);
    ordenDiasSemana.put("Jueves", 4);
    ordenDiasSemana.put("Viernes", 5);
    ordenDiasSemana.put("Sábado", 6);
    ordenDiasSemana.put("Domingo", 7);

    // Contar la cantidad de horarios por día de la semana
    Map<String, Long> horariosPorDia = horarios.stream()
            .collect(Collectors.groupingBy(Horario::getDia_semana, Collectors.counting()));

    // Ordenar el mapa resultante según el orden de los días de la semana
    Map<String, Long> horariosPorDiaOrdenados = new LinkedHashMap<>();
    ordenDiasSemana.entrySet().stream()
            .filter(entry -> horariosPorDia.containsKey(entry.getKey()))
            .forEachOrdered(entry -> horariosPorDiaOrdenados.put(entry.getKey(), horariosPorDia.get(entry.getKey())));

    return horariosPorDiaOrdenados;
}
}
