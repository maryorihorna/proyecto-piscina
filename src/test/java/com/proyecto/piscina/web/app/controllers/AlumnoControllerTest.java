package com.proyecto.piscina.web.app.controllers;

import com.proyecto.piscina.web.app.entities.Alumno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlumnoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        restTemplate.getRestTemplate().getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Test
    void getAlumnoShouldReturnAlumno() {
        ResponseEntity<Alumno> response = restTemplate.getForEntity("http://localhost:" + port + "/api/alumnos/1", Alumno.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

}