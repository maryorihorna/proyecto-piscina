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

import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.services.InstructorService;

@ExtendWith(SpringExtension.class)
public class InstructorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private InstructorController instructorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
    }

    @Test
    void listarInstructores() throws Exception {
        when(instructorService.getAllInstructors()).thenReturn(Arrays.asList(new Instructor(), new Instructor()));

        mockMvc.perform(get("/instructores"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Instructor/index"))
                .andExpect(model().attributeExists("instructores"));

        
    }

    @Test
    void showCreateForm() throws Exception {
        mockMvc.perform(get("/instructores/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CRUDS/Instructor/create"))
                .andExpect(model().attributeExists("instructor"));
    }

    @Test
    void saveInstructor() throws Exception {
        mockMvc.perform(post("/instructores")
                .param("nombre", "Instructor de prueba")
                .param("apellido", "Apellido de prueba"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/instructores"));
    }

    @Test
    void updateInstructor() throws Exception {
        mockMvc.perform(post("/instructores/update/1")
                .param("nombre", "Instructor actualizado")
                .param("apellido", "Apellido actualizado"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/instructores"));

       
    }

    @Test
    void deleteInstructor() throws Exception {
        mockMvc.perform(get("/instructores/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/instructores"));

       
    }
}