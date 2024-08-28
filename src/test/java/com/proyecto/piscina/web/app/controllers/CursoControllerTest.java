package com.proyecto.piscina.web.app.controllers;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.services.CursoService;

@ExtendWith(SpringExtension.class)
public class CursoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
    }

    @Test
    void listarCursos() throws Exception {
        when(cursoService.getAllCursos()).thenReturn(Arrays.asList(new Curso(), new Curso()));

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Curso/index"))
                .andExpect(model().attributeExists("cursos"));

       
    }

    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/cursos/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Curso/create"))
                .andExpect(model().attributeExists("curso"));
    }

    @Test
    void saveCurso() throws Exception {
        mockMvc.perform(post("/cursos")
                .param("nombre", "Curso de prueba")
                .param("descripcion", "Descripción del curso de prueba")
                .param("fechaInicio", "2023-01-01")
                .param("fechaFin", "2023-12-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cursos"));

       
    }


    @Test
    void updateCurso() throws Exception {
        mockMvc.perform(post("/cursos/update/1")
                .param("nombre", "Curso actualizado")
                .param("descripcion", "Descripción actualizada")
                .param("fechaInicio", "2023-01-01")
                .param("fechaFin", "2023-12-31"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cursos"));

    }

    @Test
    void deleteCurso() throws Exception {
        mockMvc.perform(get("/cursos/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cursos"));

        
    }
}