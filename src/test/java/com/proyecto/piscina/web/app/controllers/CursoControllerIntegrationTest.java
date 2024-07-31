package com.proyecto.piscina.web.app.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.piscina.web.app.entities.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class CursoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testCreateCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre("Curso de Natación");
        curso.setDescripcion("Aprender a nadar");
        curso.setNivel("Principiante");
        curso.setCupo_maximo(20);
        curso.setFecha_inicio(new Date());
        curso.setFecha_fin(new Date());

        mockMvc.perform(post("/api/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Curso de Natación"))
                .andExpect(jsonPath("$.descripcion").value("Aprender a nadar"))
                .andExpect(jsonPath("$.nivel").value("Principiante"))
                .andExpect(jsonPath("$.cupo_maximo").value(20));
    }
}



