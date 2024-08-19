package com.proyecto.piscina.web.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.entities.Pago;
import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.MatriculaService;
import com.proyecto.piscina.web.app.services.PagoService;
import com.proyecto.piscina.web.app.services.ProfileService;

@Controller
@RequestMapping("/alumno")
public class InterfazAlumnoController {
    @Autowired
    private ProfileService profileService;
    private final AlumnoService alumnoService;
    private final ClaseService claseService;
    private final MatriculaService matriculaService;

    private final PagoService pagoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired

    public InterfazAlumnoController(AlumnoService alumnoService, ClaseService claseService, MatriculaService matriculaService, PagoService pagoService) {
        this.alumnoService = alumnoService;
        this.claseService = claseService;
        this.matriculaService = matriculaService;
        this.pagoService = pagoService;
    }

    @GetMapping
    public String mostrarDatosAlumno(Model model) {
        Alumno alumno = profileService.getAlumnoByUsuario();
        model.addAttribute("alumno", alumno);
        return "Inicio2";
    }

    @PostMapping("/update/{id}")
    public String updateAlumno(@PathVariable("id") long id, @ModelAttribute("alumno") Alumno alumno) {
        alumnoService.updateAlumno(id, alumno);
        return "redirect:/alumno";
    }

    @GetMapping("/matricula")
    public String mostrarDatosAlumno1(Model model) {
        Matricula matricula = new Matricula();
        Alumno alumno = profileService.getAlumnoByUsuario();
        List<Clase> clases = claseService.getAllClases();
        model.addAttribute("matricula", matricula);
        model.addAttribute("alumno", alumno);
        model.addAttribute("clases", clases);
        return "matricula";
    }

    @PostMapping("/matricula")
    public String saveMatricula(@ModelAttribute("matricula") Matricula matricula) {
        Alumno alumno = profileService.getAlumnoByUsuario();
        matricula.setAlumno(alumno);
        matriculaService.saveMatricula(matricula);
        return "redirect:/alumno/matricula";
    }

    @GetMapping("/pago")
    public String showCreateForm(Model model) {
        Pago pago = new Pago();
        List<Matricula> matriculas = matriculaService.findByEstado("No Pagado"); // Filtra por estado
        Alumno alumno = profileService.getAlumnoByUsuario();
        model.addAttribute("pago", pago);
        model.addAttribute("alumno", alumno);
        model.addAttribute("matriculas", matriculas);
        return "pago"; // Redirige a la vista de crear pago
    }

    @PostMapping("/pago")
    public String savePago(@ModelAttribute("pago") Pago pago) {
        Matricula matricula = matriculaService.findById(pago.getMatricula().getIdMatricula());
        if (matricula != null) {
            matricula.setEstado("Pagado"); // Cambia el estado de la matrícula a "Pagado"
            matriculaService.update(matricula.getIdMatricula(), matricula);
        }
        pago.setFecha(new Date()); // Establece la fecha actual
		pagoService.savePago(pago);
        return "redirect:/alumno/pagos"; // Redirige al listado de pagos después de guardar
    }

}