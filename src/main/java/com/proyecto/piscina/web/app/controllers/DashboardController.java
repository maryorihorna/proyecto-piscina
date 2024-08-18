package com.proyecto.piscina.web.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.CursoService;
import com.proyecto.piscina.web.app.services.HorarioService;
import com.proyecto.piscina.web.app.services.MatriculaService;
import com.proyecto.piscina.web.app.services.PagoService;

@Controller
public class DashboardController {
    
    private AlumnoService alumnoService;
    private CursoService cursoService;
    private PagoService pagoService;
    private MatriculaService matriculaService;
    private HorarioService horarioService;


    @Autowired
    public DashboardController(HorarioService horarioService, AlumnoService alumnoService,CursoService cursoService,PagoService pagoService, MatriculaService matriculaService) {
        this.alumnoService = alumnoService;
        this.cursoService = cursoService;
        this.pagoService = pagoService;
        this.matriculaService = matriculaService;
        this.horarioService = horarioService;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/")
    public String dashboard(Model model) throws JsonProcessingException {
        long totalAlumnos = alumnoService.contarAlumnos();
        model.addAttribute("totalAlumnos", totalAlumnos);

        long totalCursos = cursoService.contarCursos();
        model.addAttribute("totalCursos", totalCursos);

        Float sumaTotal = pagoService.calcularSumaTotalMontos();
        String sumaTotalFormateada = String.format("%.2f", sumaTotal);
        model.addAttribute("sumaTotal", sumaTotalFormateada);

        float porcentajePagado = matriculaService.calcularPorcentajePagado();
        String porcentajePagadoFormateado = String.format("%.2f", porcentajePagado);
        model.addAttribute("porcentajePagado", porcentajePagadoFormateado);

        Map<String, Long> cursosPorNivel = cursoService.contarCursosPorNivel();
        String cursosPorNivelJson = objectMapper.writeValueAsString(cursosPorNivel);
        model.addAttribute("cursosPorNivelJson", cursosPorNivelJson);

        Map<String, Long> horariosPorDia = horarioService.contarHorariosPorDia();
        String horariosPorDiaJson = objectMapper.writeValueAsString(horariosPorDia);
        model.addAttribute("horariosPorDiaJson", horariosPorDiaJson);

        Map<String, Long> matriculasPorMes = matriculaService.contarMatriculasPorMes();
        String matriculasPorMesJson = objectMapper.writeValueAsString(matriculasPorMes);
        model.addAttribute("matriculasPorMesJson", matriculasPorMesJson);

        return "inicio"; // Nombre del archivo HTML.
    }
}
