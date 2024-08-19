package com.proyecto.piscina.web.app.controllers;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.services.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AlumnoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
    }

    @Test
    void listarAlumnos() throws Exception {
        when(alumnoService.getAllAlumnos()).thenReturn(Arrays.asList(new Alumno(), new Alumno()));

        mockMvc.perform(get("/alumnos"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Alumno/index"))
                .andExpect(model().attributeExists("alumnos"));

        verify(alumnoService, times(1)).getAllAlumnos();
    }

    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/alumnos/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Alumno/create"))
                .andExpect(model().attributeExists("alumno"));
    }
    @Test
    void saveAlumno() throws Exception {
        // Simula el comportamiento del método saveAlumno
        when(alumnoService.saveAlumno(any(Alumno.class))).thenReturn(new Alumno());

        mockMvc.perform(post("/alumnos")
                .param("nombre", "Juan")
                .param("apellido", "Lopez")
                .param("email", "juan@gmail.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alumnos"));

        verify(alumnoService, times(1)).saveAlumno(any(Alumno.class));
    }


    @Test
    void showUpdateForm() throws Exception {
        Alumno alumno = new Alumno();
        alumno.setIdalumno(1L);
        when(alumnoService.getAlumno(1L)).thenReturn(Optional.of(alumno));

        mockMvc.perform(get("/alumnos/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Alumno/update"))
                .andExpect(model().attributeExists("alumno"));

        verify(alumnoService, times(1)).getAlumno(1L);
    }

    @Test
    void updateAlumno() throws Exception {
        // Simula el comportamiento del método updateAlumno
        when(alumnoService.updateAlumno(anyLong(), any(Alumno.class))).thenReturn(new Alumno());
    
        mockMvc.perform(post("/alumnos/update/1")
                .param("nombre", "Juan")
                .param("apellido", "Lopez")
                .param("email", "juan@gmail.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alumnos"));
    
        verify(alumnoService, times(1)).updateAlumno(anyLong(), any(Alumno.class));
    }

    @Test
    void deleteAlumno() throws Exception {
        doNothing().when(alumnoService).deleteAlumno(1L);

        mockMvc.perform(get("/alumnos/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alumnos"));

        verify(alumnoService, times(1)).deleteAlumno(1L);
    }
}