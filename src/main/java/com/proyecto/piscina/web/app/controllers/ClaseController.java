package com.proyecto.piscina.web.app.controllers;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.CursoService;
import com.proyecto.piscina.web.app.services.InstructorService;
import com.proyecto.piscina.web.app.utils.CustomTimeEditor;

@Controller
@RequestMapping("/clases")
public class ClaseController {

    private final ClaseService claseService;
    private final CursoService cursoService;
    private final InstructorService instructorService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new CustomTimeEditor("HH:mm", false));
        // Registrar un CustomDateEditor para manejar las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    public ClaseController(ClaseService claseService, CursoService cursoService, InstructorService instructorService) {
        this.claseService = claseService;
        this.cursoService = cursoService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String listarClases(Model model) {
        List<Clase> clases = claseService.getAllClases();
        model.addAttribute("clases", clases);
        return "CRUDS/Clase/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Clase clase = new Clase();
        List<Curso> cursos = cursoService.getAllCursos();
        List<Instructor> instructores = instructorService.getAllInstructors();
        model.addAttribute("clase", clase);
        model.addAttribute("cursos", cursos);
        model.addAttribute("instructores", instructores);
        return "CRUDS/Clase/create";
    }

    @PostMapping
    public String saveClase(@ModelAttribute("clase") Clase clase) {
        claseService.saveClase(clase);
        return "redirect:/clases";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Clase clase = claseService.getClase(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de clase inválido: " + id));
        List<Curso> cursos = cursoService.getAllCursos();
        List<Instructor> instructores = instructorService.getAllInstructors();
        model.addAttribute("clase", clase);
        model.addAttribute("cursos", cursos);
        model.addAttribute("instructores", instructores);
        return "CRUDS/Clase/update";
    }

    @PostMapping("/update/{id}")
    public String updateClase(@PathVariable("id") long id, @ModelAttribute("clase") Clase clase) {
        claseService.updateClase(id, clase);
        return "redirect:/clases";
    }

    @GetMapping("/delete/{id}")
    public String deleteClase(@PathVariable("id") long id) {
        claseService.deleteClase(id);
        return "redirect:/clases";
    }
}