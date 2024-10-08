package com.proyecto.piscina.web.app.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.proyecto.piscina.web.app.entities.Administrador;
import com.proyecto.piscina.web.app.services.AdministradorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @GetMapping
    public String listarAdministradores(Model model) {
        List<Administrador> administradores = administradorService.getAllAdministradores();
        model.addAttribute("administradores", administradores);
        return "CRUDS/Administrador/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Administrador administrador = new Administrador();
        model.addAttribute("administrador", administrador);
        return "CRUDS/Administrador/create";
    }

    @PostMapping
    public String saveAdministrador( @Valid @ModelAttribute("administrador") Administrador administrador,BindingResult result, Model model ) {
        if (result.hasErrors()) {
            return "CRUDS/Administrador/create"; // Asegúrate de que esta ruta sea correcta
        }
        administradorService.saveAdministrador(administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Administrador administrador = administradorService.getAdministrador(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de administrador inválido:" + id));
        model.addAttribute("administrador", administrador);
        return "CRUDS/Administrador/update";
    }

    @PostMapping("/update/{id}")
    public String updateAdministrador(@PathVariable("id") long id, @ModelAttribute("administrador") Administrador administrador) {
        administradorService.updateAdministrador(id, administrador);
        return "redirect:/administradores";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdministrador(@PathVariable("id") long id) {
        administradorService.deleteAdministrador(id);
        return "redirect:/administradores";
    }
}