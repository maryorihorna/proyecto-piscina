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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.entities.Pago;
import com.proyecto.piscina.web.app.services.MatriculaService;
import com.proyecto.piscina.web.app.services.PagoService;

@Controller
@RequestMapping("pagos")
public class PagoController {

    private final PagoService pagoService;
    private final MatriculaService matriculaService;

	public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

	@Autowired
    public PagoController(PagoService pagoService, MatriculaService matriculaService) {
        this.pagoService = pagoService;
        this.matriculaService = matriculaService;
    }

    @GetMapping
    public String listarPagos(Model model) {
        List<Pago> pagos = pagoService.findAll();
        model.addAttribute("pagos", pagos);
        return "CRUDS/Pago/index"; // Redirige a la vista de listado de pagos
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Pago pago = new Pago();
        List<Matricula> matriculas = matriculaService.findAll();
        model.addAttribute("pago", pago);
        model.addAttribute("matriculas", matriculas);
        return "CRUDS/Pago/create"; // Redirige a la vista de crear pago
    }

    @PostMapping
    public String savePago(@ModelAttribute("pago") Pago pago) {
        pago.setFecha(new Date()); // Establece la fecha actual
		pagoService.savePago(pago);
        return "redirect:/pagos"; // Redirige al listado de pagos después de guardar
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Pago pago = pagoService.getPago(id)
			.orElseThrow(() -> new IllegalArgumentException("Id de horario inválido:" + id));
        List<Matricula> matriculas = matriculaService.findAll();
        model.addAttribute("pago", pago);
        model.addAttribute("matriculas", matriculas);
        return "CRUDS/Pago/update"; // Redirige a la vista de actualizar pago
    }

    @PostMapping("/update/{id}")
    public String updatePago(@PathVariable("id") long id, @ModelAttribute("pago") Pago pago) {
        pagoService.update(id, pago);
        return "redirect:/pagos"; // Redirige al listado de pagos después de actualizar
    }

    @GetMapping("/delete/{id}")
    public String deletePago(@PathVariable("id") long id) {
        pagoService.deleteById(id);
        return "redirect:/pagos"; // Redirige al listado de pagos después de eliminar
    }
	
	
	// @Autowired
	//     private PagoService pagoService;

	//     @GetMapping
	//     public List<Pago> getAllPagos() {
	//         return pagoService.findAll();
	//     }

	//     @GetMapping("/{id}")
	//     public Pago getPagoById(@PathVariable Long id) {
	//         return pagoService.findById(id);
	//     }

	//     @PostMapping
	//     public Pago createPago(@RequestBody Pago pago) {
	//         return pagoService.savePago(pago);
	//     }

	//     @PutMapping("/{id}")
	//     public Pago updatePago(@PathVariable Long id, @RequestBody Pago pago) {
	//         return pagoService.update(id, pago);
	//     }

	//     @DeleteMapping("/{id}")
	//     public void deletePago(@PathVariable Long id) {
	//         pagoService.deleteById(id);
	//     }
}
