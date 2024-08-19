package com.proyecto.piscina.web.app.controllers;

import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.services.ClaseService;
import com.proyecto.piscina.web.app.services.CursoService;
import com.proyecto.piscina.web.app.services.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class ClaseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClaseService claseService;

    @Mock
    private CursoService cursoService;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private ClaseController claseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(claseController).build();
    }

    @Test
    void listarClases() throws Exception {
        when(claseService.getAllClases()).thenReturn(Arrays.asList(new Clase(), new Clase()));

        mockMvc.perform(get("/clases"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Clase/index"))
                .andExpect(model().attributeExists("clases"));

        
    }
    
    @Test
    void showCreateForm() throws Exception {
        when(cursoService.getAllCursos()).thenReturn(Arrays.asList(new Curso(), new Curso()));
        when(instructorService.getAllInstructors()).thenReturn(Arrays.asList(new Instructor(), new Instructor()));
    
        mockMvc.perform(get("/clases/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Clase/create"))
                .andExpect(model().attributeExists("clase"))
                .andExpect(model().attributeExists("cursos"))
                .andExpect(model().attributeExists("instructores"));
    }

    @Test
    void createClase() throws Exception {
        Clase nuevaClase = new Clase();
        when(claseService.saveClase(any(Clase.class))).thenReturn(nuevaClase);

        mockMvc.perform(post("/clases")
                .param("nombre", "Clase de prueba")
                .param("descripcion", "Descripción de prueba"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clases"));


    }

    @Test
    void updateClase() throws Exception {
        Clase claseActualizada = new Clase();
        when(claseService.updateClase(anyLong(), any(Clase.class))).thenReturn(claseActualizada);

        mockMvc.perform(post("/clases/update/1")
                .param("nombre", "Clase actualizada")
                .param("descripcion", "Descripción actualizada"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clases"));

    }
}