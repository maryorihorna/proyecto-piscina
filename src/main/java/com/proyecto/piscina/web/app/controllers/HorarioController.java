package com.proyecto.piscina.web.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.services.*;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {
	 
	private final HorarioService horarioService;

	    public HorarioController(HorarioService horarioService) {
	        this.horarioService = horarioService;
	    }

	    @GetMapping
	    public List<Horario> getAllHorarios() {
	        return horarioService.getAllHorarios();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Horario> getHorarioById(@PathVariable long id) {
	        Optional<Horario> horario = horarioService.getHorario(id);
	        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
	        Horario nuevoHorario = horarioService.saveHorario(horario);
	        return ResponseEntity.ok(nuevoHorario);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Horario> updateHorario(@PathVariable long id, @RequestBody Horario horarioDetails) {
	        Horario updatedHorario = horarioService.updateHorario(id, horarioDetails);
	        return ResponseEntity.ok(updatedHorario);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteHorario(@PathVariable long id) {
	        horarioService.deleteHorario(id);
	        return ResponseEntity.noContent().build();
	    }
}
