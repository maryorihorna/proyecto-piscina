package com.proyecto.piscina.web.app.controllers;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.piscina.web.app.services.MatriculaService;
import com.proyecto.piscina.web.app.util.reportes.MatriculasPDF;
import com.proyecto.piscina.web.app.entities.Matricula;


import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/reportes")
public class ReportesController {
    
    private final MatriculaService matriculaService;

    public ReportesController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }
    
    @GetMapping("/matriculasPDF")
    public void exportarListaMatriculas(HttpServletResponse response) {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Matriculas_"+fechaActual+".pdf";

        response.setHeader(cabecera,valor);

        List<Matricula> ListaMatriculas = matriculaService.findAll();

        MatriculasPDF exporter = new MatriculasPDF(ListaMatriculas);
        
        exporter.generarPDF(response);
    }
}
