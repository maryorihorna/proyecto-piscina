package com.proyecto.piscina.web.app.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.respository.AlumnoRepository;
import com.proyecto.piscina.web.app.respository.ClaseRepository;
import com.proyecto.piscina.web.app.respository.MatriculaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MatriculaServiceIntegrationTest {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ClaseRepository claseRepository;

    private Alumno testAlumno;
    private Clase testClase;

    @BeforeEach
    public void setUp() {
        // Crear datos de prueba
        testAlumno = new Alumno();
        testAlumno.setNombre("Juan");
        testAlumno.setDireccion("Calle Falsa 123");
        testAlumno.setFecha_nacimiento(new Date());
        testAlumno = alumnoRepository.save(testAlumno);

        testClase = new Clase();
        testClase.setFecha(new Date());
        testClase.setHoraInicio(new Time(System.currentTimeMillis()));
        testClase.setHoraFin(new Time(System.currentTimeMillis() + 3600000)); // 1 hora después
        testClase = claseRepository.save(testClase);
    }

    @Test
    public void testFindAll() {
        List<Matricula> matriculas = matriculaService.findAll();
        assertThat(matriculas).isNotNull();
    }

    @Test
    public void testFindById() {
        Matricula matricula = new Matricula();
        matricula.setAlumno(testAlumno);
        matricula.setClase(testClase);
        matricula.setFechaMatricula(new Date());
        matricula = matriculaRepository.save(matricula);

        Matricula foundMatricula = matriculaService.findById(matricula.getIdMatricula());
        assertThat(foundMatricula).isNotNull();
        assertThat(foundMatricula.getIdMatricula()).isEqualTo(matricula.getIdMatricula());
    }

    @Test
    public void testSaveMatricula() {
        Matricula matricula = new Matricula();
        matricula.setAlumno(testAlumno);
        matricula.setClase(testClase);
        matricula.setFechaMatricula(new Date());

        Matricula savedMatricula = matriculaService.saveMatricula(matricula);
        assertThat(savedMatricula).isNotNull();
        assertThat(savedMatricula.getIdMatricula()).isNotNull();
    }

    @Test
    public void testDeleteById() {
        Matricula matricula = new Matricula();
        matricula.setAlumno(testAlumno);
        matricula.setClase(testClase);
        matricula.setFechaMatricula(new Date());
        matricula = matriculaRepository.save(matricula);

        matriculaService.deleteById(matricula.getIdMatricula());

        Optional<Matricula> deletedMatricula = matriculaRepository.findById(matricula.getIdMatricula());
        assertThat(deletedMatricula).isEmpty();
    }

    @Test
    public void testUpdate() {
        Matricula matricula = new Matricula();
        matricula.setAlumno(testAlumno);
        matricula.setClase(testClase);
        matricula.setFechaMatricula(new Date());
        matricula = matriculaRepository.save(matricula);

        Matricula updatedMatricula = new Matricula();
        updatedMatricula.setAlumno(testAlumno);
        updatedMatricula.setClase(testClase);
        updatedMatricula.setEstado("Actualizado");

        Matricula result = matriculaService.update(matricula.getIdMatricula(), updatedMatricula);
        assertThat(result).isNotNull();
        assertThat(result.getEstado()).isEqualTo("Actualizado");
    }

    @Test
    public void testFindByIdNotFound() {
        Long nonExistentId = 999L;
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            matriculaService.findById(nonExistentId);
        });

        String expectedMessage = "Matrícula no encontrada con ID: " + nonExistentId;
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}