package com.proyecto.piscina.web.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.services.CursoService;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        List<Curso> cursos = cursoService.getAllCursos();
        model.addAttribute("cursos", cursos);
        return "CRUDS/Curso/index"; // Redirige a la vista de listado de cursos
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        return "CRUDS/Curso/create"; // Redirige a la vista de crear curso
    }

    @PostMapping
    public String saveCurso(@ModelAttribute("curso") Curso curso) {
        cursoService.saveCurso(curso);
        return "redirect:/cursos"; // Redirige al listado de cursos después de guardar
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Curso curso = cursoService.getCurso(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de curso inválido:" + id));
        model.addAttribute("curso", curso);
        return "CRUDS/Curso/update"; // Redirige a la vista de editar curso
    }

    @PostMapping("/update/{id}")
    public String updateCurso(@PathVariable("id") long id, @ModelAttribute("curso") Curso curso) {
        cursoService.updateCurso(id, curso);
        return "redirect:/cursos"; // Redirige al listado de cursos después de actualizar
    }

    @GetMapping("/delete/{id}")
    public String deleteCurso(@PathVariable("id") long id) {
        cursoService.deleteCurso(id);
        return "redirect:/cursos"; // Redirige al listado de cursos después de eliminar
    }


    // @GetMapping
    // public List<Curso> getAllCursos() {
    //     return cursoService.getAllCursos();
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<Curso> getCursoById(@PathVariable long id) {
    //     Optional<Curso> curso = cursoService.getCurso(id);
    //     return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    // }

    // @PostMapping
    // public Curso createCurso(@RequestBody Curso curso) {
    //     return cursoService.saveCurso(curso);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Curso> updateCurso(@PathVariable long id, @RequestBody Curso cursoDetails) {
    //     Curso updatedCurso = cursoService.updateCurso(id, cursoDetails);
    //     return ResponseEntity.ok(updatedCurso);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteCurso(@PathVariable long id) {
    //     cursoService.deleteCurso(id);
    //     return ResponseEntity.noContent().build();
    // }
}
