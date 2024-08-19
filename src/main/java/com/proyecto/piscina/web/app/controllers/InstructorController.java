package com.proyecto.piscina.web.app.controllers;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.services.InstructorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/instructores")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public String listarInstructores(Model model) {
        List<Instructor> instructores = instructorService.getAllInstructors();
        model.addAttribute("instructores", instructores);
        return "CRUDS/Instructor/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Instructor instructor = new Instructor();
        model.addAttribute("instructor", instructor);
        return "CRUDS/Instructor/create";
    }

    @PostMapping
    public String saveInstructor(@Valid @ModelAttribute("instructor") Instructor instructor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CRUDS/Instructor/create"; // Asegúrate de que esta ruta sea correcta
        }
        instructorService.saveInstructor(instructor);
        return "redirect:/instructores";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Instructor instructor = instructorService.getInstructor(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de instructor inválido:" + id));
        model.addAttribute("instructor", instructor);
        return "CRUDS/Instructor/update";
    }

    @PostMapping("/update/{id}")
    public String updateInstructor(@PathVariable("id") long id, @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructores";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable("id") long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructores";
    }
}