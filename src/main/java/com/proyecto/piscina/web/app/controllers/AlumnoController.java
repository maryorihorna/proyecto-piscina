package com.proyecto.piscina.web.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.services.AlumnoService;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumno(@PathVariable long id) {
        Optional<Alumno> alumno = alumnoService.getAlumno(id);
        if (alumno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alumno.get());
    }

    @PostMapping
    public ResponseEntity<Alumno> saveAlumno(@RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.saveAlumno(alumno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable long id, @RequestBody Alumno alumno) {
        return ResponseEntity.ok(alumnoService.updateAlumno(id, alumno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable long id) {
        alumnoService.deleteAlumno(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos() {
        return ResponseEntity.ok(alumnoService.getAllAlumnos());
    }
}