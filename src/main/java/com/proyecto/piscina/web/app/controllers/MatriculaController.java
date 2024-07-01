package com.proyecto.piscina.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.services.MatriculaService;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {
	 @Autowired
	    private MatriculaService matriculaService;

	    @GetMapping
	    public List<Matricula> getAllMatriculas() {
	        return matriculaService.findAll();
	    }

	    @GetMapping("/{id}")
	    public Matricula getMatriculaById(@PathVariable Long id) {
	        return matriculaService.findById(id);
	    }

	    @PostMapping
	    public Matricula createMatricula(@RequestBody Matricula matricula) {
	        return matriculaService.saveMatricula(matricula);
	    }

	    @PutMapping("/{id}")
	    public Matricula updateMatricula(@PathVariable Long id, @RequestBody Matricula matricula) {
	        return matriculaService.update(id, matricula);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteMatricula(@PathVariable Long id) {
	        matriculaService.deleteById(id);
	    }
}
