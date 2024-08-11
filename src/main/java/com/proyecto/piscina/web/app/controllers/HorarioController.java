package com.proyecto.piscina.web.app.controllers;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.piscina.web.app.entities.Horario;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.services.HorarioService;
import com.proyecto.piscina.web.app.services.InstructorService;
import com.proyecto.piscina.web.app.utils.CustomTimeEditor;

@Controller
@RequestMapping("/horarios")
public class HorarioController {
    
	private final HorarioService horarioService;
    private final InstructorService instructorService;

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Time.class, new CustomTimeEditor("HH:mm", false));
    }

    @Autowired
    public HorarioController(HorarioService horarioService, InstructorService instructorService) {
        this.horarioService = horarioService;
        this.instructorService = instructorService;
    }

    @GetMapping
    public String listarHorarios(Model model) {
        List<Horario> horarios = horarioService.getAllHorarios();
        model.addAttribute("horarios", horarios);
        return "CRUDS/Horario/index"; // Redirige a la vista de listado de horarios
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Horario horario = new Horario();
        List<Instructor> instructores = instructorService.getAllInstructors();
        model.addAttribute("horario", horario);
        model.addAttribute("instructores", instructores);
        return "CRUDS/Horario/create"; // Redirige a la vista de crear horario
    }

    @PostMapping
    public String saveHorario(@ModelAttribute("horario") Horario horario) {
        horarioService.saveHorario(horario);
        return "redirect:/horarios"; // Redirige al listado de horarios después de guardar
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Horario horario = horarioService.getHorario(id)
            .orElseThrow(() -> new IllegalArgumentException("Id de horario inválido:" + id));
        List<Instructor> instructores = instructorService.getAllInstructors();
        model.addAttribute("horario", horario);
        model.addAttribute("instructores", instructores);
        return "CRUDS/Horario/update"; // Redirige a la vista de actualizar horario
    }

    @PostMapping("/update/{id}")
    public String updateHorario(@PathVariable("id") long id, @ModelAttribute("horario") Horario horario) {
        horarioService.updateHorario(id, horario);
        return "redirect:/horarios"; // Redirige al listado de horarios después de actualizar
    }

    @GetMapping("/delete/{id}")
    public String deleteHorario(@PathVariable("id") long id) {
        horarioService.deleteHorario(id);
        return "redirect:/horarios"; // Redirige al listado de horarios después de eliminar
    }
	// private final HorarioService horarioService;

	//     public HorarioController(HorarioService horarioService) {
	//         this.horarioService = horarioService;
	//     }

	//     @GetMapping
	//     public List<Horario> getAllHorarios() {
	//         return horarioService.getAllHorarios();
	//     }

	//     @GetMapping("/{id}")
	//     public ResponseEntity<Horario> getHorarioById(@PathVariable long id) {
	//         Optional<Horario> horario = horarioService.getHorario(id);
	//         return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	//     }

	//     @PostMapping
	//     public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
	//         Horario nuevoHorario = horarioService.saveHorario(horario);
	//         return ResponseEntity.ok(nuevoHorario);
	//     }

	//     @PutMapping("/{id}")
	//     public ResponseEntity<Horario> updateHorario(@PathVariable long id, @RequestBody Horario horarioDetails) {
	//         Horario updatedHorario = horarioService.updateHorario(id, horarioDetails);
	//         return ResponseEntity.ok(updatedHorario);
	//     }

	//     @DeleteMapping("/{id}")
	//     public ResponseEntity<Void> deleteHorario(@PathVariable long id) {
	//         horarioService.deleteHorario(id);
	//         return ResponseEntity.noContent().build();
	//     }
}
