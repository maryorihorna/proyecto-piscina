package com.proyecto.piscina.web.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.services.ClaseService;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @GetMapping
    public List<Clase> getAllClases() {
        return claseService.findAll();
    }

    @GetMapping("/{id}")
    public Clase getClaseById(@PathVariable Long id) {
        return claseService.findById(id);
    }

    @PostMapping
    public Clase createClase(@RequestBody Clase clase) {
        return claseService.saveClase(clase);
    }

    @PutMapping("/{id}")
    public Clase updateClase(@PathVariable Long id, @RequestBody Clase clase) {
        clase.setId(id);
        return claseService.saveClase(clase);
    }

    @DeleteMapping("/{id}")
    public void deleteClase(@PathVariable Long id) {
        claseService.deleteById(id);
    }
}
