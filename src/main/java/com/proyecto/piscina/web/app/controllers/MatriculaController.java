package com.proyecto.piscina.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.MatriculaService;

import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

	    private final MatriculaService matriculaService;
		private final AlumnoService alumnoService;
		private final ClaseService claseService;

		public MatriculaController(MatriculaService matriculaService, AlumnoService alumnoService, ClaseService claseService) {
	        this.matriculaService = matriculaService;		
			this.alumnoService = alumnoService;
			this.claseService = claseService;
		}

	    @GetMapping
		public String ListarMatriculas(Model model){
			List<Matricula> matriculas = matriculaService.findAll();
			model.addAttribute("matriculas", matriculas);
			return "CRUDS/Matricula/index";
		}

	    @GetMapping("/create")
		public String showCreateForm(Model model) {
			Matricula matricula = new Matricula();
			List<Alumno> alumnos = alumnoService.getAllAlumnos();
			List<Clase> clases = claseService.getAllClases();
			model.addAttribute("matricula", matricula);
			model.addAttribute("alumnos", alumnos);
			model.addAttribute("clases", clases);
			
			return "CRUDS/Matricula/create";
		}

		@PostMapping
		public String saveMatricula(@ModelAttribute("matricula") Matricula matricula) {
			matricula.setFechaMatricula(new Date());
			matriculaService.saveMatricula(matricula);
			return "redirect:/matriculas";
		}

		@GetMapping("/edit/{id}")
		public String showUpdateForm(@PathVariable("id") Long id, Model model) {
			Matricula matricula = matriculaService.findById(id);
			List<Alumno> alumnos = alumnoService.getAllAlumnos();
			List<Clase> clases = claseService.getAllClases();
			model.addAttribute("matricula", matricula);
			model.addAttribute("alumnos", alumnos);
			model.addAttribute("clases", clases);
			return "CRUDS/Matricula/update";
		}

		@PostMapping("/update/{id}")
		public String updateMatricula(@PathVariable("id") Long id, @ModelAttribute("matricula") Matricula matricula) {
			matriculaService.update(id, matricula);
			return "redirect:/matriculas";
		}

		@GetMapping("/delete/{id}")
		public String deleteMatricula(@PathVariable("id") Long id) {
			matriculaService.deleteById(id);
			return "redirect:/matriculas";
		}


	    public List<Matricula> getAllMatriculas() {
	        return matriculaService.findAll();
	    }

	    @GetMapping("/{id}")
	    public Matricula getMatriculaById(@PathVariable Long id) {
	        return matriculaService.findById(id);
	    }

}
