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

import com.proyecto.piscina.web.app.entities.Administrador;
import com.proyecto.piscina.web.app.services.AdministradorService;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministrador(@PathVariable long id) {
        Optional<Administrador> administrador = administradorService.getAdministrador(id);
        if (administrador.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administrador.get());
    }

    @PostMapping
    public ResponseEntity<Administrador> saveAdministrador(@RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.saveAdministrador(administrador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable long id, @RequestBody Administrador administrador) {
        return ResponseEntity.ok(administradorService.updateAdministrador(id, administrador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrador> deleteAdministrador(@PathVariable long id) {
        administradorService.deleteAdministrador(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> getAdministradores() {
        return ResponseEntity.ok(administradorService.getAllAdministradores());
    }
}