package com.proyecto.piscina.web.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.piscina.web.app.controllers.AdministradorController;
import com.proyecto.piscina.web.app.controllers.AlumnoController;
import com.proyecto.piscina.web.app.controllers.HorarioController;

import com.proyecto.piscina.web.app.services.AdministradorService;
import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.HorarioService;
import com.proyecto.piscina.web.app.services.MatriculaService;

@SpringBootTest
class ProyectoPiscinaApplicationTests {

    // Inyección de dependencias de los servicios
    @Autowired
    private ClaseService claseService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private HorarioService horarioService;

    @Autowired 
    private MatriculaService matriculaService;

    // Inyección de dependencias de los controladores
    @Autowired
    private AdministradorController administradorController;

    @Autowired
    private AlumnoController alumnoController;

    @Autowired
    private HorarioController horarioController; 

    @Autowired
    private AdministradorController PagoController;



    // Prueba para verificar que la aplicación se inicie correctamente
    @Test
    void inicioCorrectoProyectoTestUnit() throws Exception {
        ProyectoPiscinaApplication.main(new String[] {});
    }

    // Prueba para verificar que ClaseService no es null
    @Test
    void FuncionamientoClaseServiceTestUnit() {
        assertNotNull(claseService);
    }

    // Prueba para verificar que AlumnoService no es null
    @Test
    void FuncionamientoAlumnoServiceTestUnit() {
        assertNotNull(alumnoService);
    }

    // Prueba para verificar que AdministradorService no es null
    @Test
    void FuncionamientoAdministradorServiceTestUnit() {
        assertNotNull(administradorService);
    }

    // Prueba para verificar que HorarioService no es null
    @Test
    void FuncionamientoHorarioServiceTestUnit() {
        assertNotNull(horarioService);
    }

    // Prueba para verificar que Matricula no es null
    @Test
    void FuncionamientoMatriculaTestUnit() {
        assertNotNull(matriculaService);
    }

    // Prueba para verificar que AdministradorController no es null
    @Test
    void FuncionamientoAdministradorControllerTestUnit() {
        assertNotNull(administradorController);
    }

    // Prueba para verificar que AlumnoController no es null
    @Test
    void FuncionamientoAlumnoControllerTestUnit() {
        assertNotNull(alumnoController);
    }

    // Prueba para verificar que HorarioController no es null
    @Test
    void FuncionamientoHorarioControllerTestUnit() {
        assertNotNull(horarioController);
    }

    // Prueba para verificar que PagoController no es null
    @Test
    void FuncionamientoPagoControllerTestUnit() {
        assertNotNull(PagoController);
    }

 
}