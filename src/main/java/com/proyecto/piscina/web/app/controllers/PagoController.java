package com.proyecto.piscina.web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyecto.piscina.web.app.entities.*;
import com.proyecto.piscina.web.app.services.*;


import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
	 @Autowired
	    private PagoService pagoService;

	    @GetMapping
	    public List<Pago> getAllPagos() {
	        return pagoService.findAll();
	    }

	    @GetMapping("/{id}")
	    public Pago getPagoById(@PathVariable Long id) {
	        return pagoService.findById(id);
	    }

	    @PostMapping
	    public Pago createPago(@RequestBody Pago pago) {
	        return pagoService.savePago(pago);
	    }

	    @PutMapping("/{id}")
	    public Pago updatePago(@PathVariable Long id, @RequestBody Pago pago) {
	        return pagoService.update(id, pago);
	    }

	    @DeleteMapping("/{id}")
	    public void deletePago(@PathVariable Long id) {
	        pagoService.deleteById(id);
	    }
}
