package com.proyecto.piscina.web.app.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.respository.CursoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CursoServiceIntegrationTest {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository cursoRepository;

    private Curso testCurso;

    @BeforeEach
    public void setUp() {
        // Crear datos de prueba
        testCurso = new Curso();
        testCurso.setNombre("Curso de Natación");
        testCurso.setDescripcion("Curso básico de natación");
        testCurso.setNivel("Básico");
        testCurso.setCupo_maximo(20);
        testCurso.setFecha_inicio(new Date());
        testCurso.setFecha_fin(new Date(System.currentTimeMillis() + 86400000L * 30)); // 30 días después
        testCurso = cursoRepository.save(testCurso);
    }

    @Test
    public void testSaveCurso() {
        Curso curso = new Curso();
        curso.setNombre("Curso de Buceo");
        curso.setDescripcion("Curso avanzado de buceo");
        curso.setNivel("Avanzado");
        curso.setCupo_maximo(10);
        curso.setFecha_inicio(new Date());
        curso.setFecha_fin(new Date(System.currentTimeMillis() + 86400000L * 60)); // 60 días después
        Curso savedCurso = cursoService.saveCurso(curso);
        assertThat(savedCurso).isNotNull();
        assertThat(savedCurso.getId_curso()).isNotNull();
    }

    @Test
    public void testUpdateCurso() {
        Curso updatedCurso = new Curso();
        updatedCurso.setNombre("Curso Avanzado");
        updatedCurso.setDescripcion("Curso avanzado de natación");
        updatedCurso.setNivel("Avanzado");
        updatedCurso.setCupo_maximo(15);
        updatedCurso.setFecha_inicio(new Date());
        updatedCurso.setFecha_fin(new Date(System.currentTimeMillis() + 86400000L * 45)); // 45 días después
        Curso result = cursoService.updateCurso(testCurso.getId_curso(), updatedCurso);
        assertThat(result).isNotNull();
        assertThat(result.getNombre()).isEqualTo("Curso Avanzado");
    }

    @Test
    public void testGetCurso() {
        Curso foundCurso = cursoService.getCurso(testCurso.getId_curso());
        assertThat(foundCurso).isNotNull();
        assertThat(foundCurso.getId_curso()).isEqualTo(testCurso.getId_curso());
    }

    @Test
    public void testGetAllCursos() {
        List<Curso> cursos = cursoService.getAllCursos();
        assertThat(cursos).isNotEmpty();
    }

    @Test
    public void testDeleteCurso() {
        cursoService.deleteCurso(testCurso.getId_curso());

        Optional<Curso> deletedCurso = cursoRepository.findById(testCurso.getId_curso());
        assertThat(deletedCurso).isEmpty();
    }

    @Test
    public void testGetCursoNotFound() {
        Long nonExistentId = 999L;
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            cursoService.getCurso(nonExistentId);
        });

        String expectedMessage = "El curso con id " + nonExistentId + " no existe";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}