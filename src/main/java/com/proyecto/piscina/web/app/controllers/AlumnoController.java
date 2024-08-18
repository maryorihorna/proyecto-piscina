package com.proyecto.piscina.web.app.controllers;

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

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.services.AlumnoService;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public String listarAlumnos(Model model) {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        model.addAttribute("alumnos", alumnos);
        return "CRUDS/Alumno/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Alumno alumno = new Alumno();
        model.addAttribute("alumno", alumno);
        return "CRUDS/Alumno/create";
    }

    @PostMapping
    public String saveAlumno(@ModelAttribute("alumno") Alumno alumno) {
        alumnoService.saveAlumno(alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Alumno alumno = alumnoService.getAlumno(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de alumno inválido:" + id));
        model.addAttribute("alumno", alumno);
        return "CRUDS/Alumno/update";
    }

    @PostMapping("/update/{id}")
    public String updateAlumno(@PathVariable("id") long id, @ModelAttribute("alumno") Alumno alumno) {
        alumnoService.updateAlumno(id, alumno);
        return "redirect:/alumnos";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlumno(@PathVariable("id") long id) {
        alumnoService.deleteAlumno(id);
        return "redirect:/alumnos";
    }
}