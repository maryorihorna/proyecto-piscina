package com.proyecto.piscina.web.app;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.proyecto.piscina.web.app.services.AdministradorService;
import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.CursoService;
import com.proyecto.piscina.web.app.services.HorarioService;
import com.proyecto.piscina.web.app.services.InstructorService;
import com.proyecto.piscina.web.app.services.MatriculaService;
import com.proyecto.piscina.web.app.services.PagoService;

@SpringBootTest
class ProyectoPiscinaApplicationTests {
	@Autowired
    private ClaseService claseService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private PagoService pagoService;


	@Test
	void contextLoads() throws Exception {
		// Verifica que la aplicación se inicie correctamente
		ProyectoPiscinaApplication.main(new String[] {});
	}

    @Test
    void testClaseService() {
        assertNotNull(claseService);
    }






}
