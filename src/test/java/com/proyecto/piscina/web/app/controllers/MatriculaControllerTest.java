package com.proyecto.piscina.web.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.proyecto.piscina.web.app.services.AlumnoService;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.MatriculaService;

@ExtendWith(SpringExtension.class)
public class MatriculaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MatriculaService matriculaService;

    @Mock
    private AlumnoService alumnoService;

    @Mock
    private ClaseService claseService;

    @InjectMocks
    private MatriculaController matriculaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(matriculaController).build();
    }

    @Test
    void listarMatriculas() throws Exception {
        mockMvc.perform(get("/matriculas"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Matricula/index"))
                .andExpect(model().attributeExists("matriculas"));
    }

    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/matriculas/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Matricula/create"))
                .andExpect(model().attributeExists("matricula"))
                .andExpect(model().attributeExists("alumnos"))
                .andExpect(model().attributeExists("clases"));
    }

    @Test
    void deleteMatricula() throws Exception {
        mockMvc.perform(get("/matriculas/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/matriculas"));

    }
}