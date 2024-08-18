package com.proyecto.piscina.web.app.services;

import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.entities.Instructor;
import com.proyecto.piscina.web.app.respository.ClaseRepository;
import com.proyecto.piscina.web.app.respository.CursoRepository;
import com.proyecto.piscina.web.app.respository.InstructorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ClaseServiceUpdateIntegrationTest {

    @Autowired
    private ClaseService claseService;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @BeforeEach
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba si es necesario
        claseRepository.deleteAll();
        cursoRepository.deleteAll();
        instructorRepository.deleteAll();
    }

    @Test
    public void testUpdateClase() {
        // Datos de prueba
        Long claseId = 1L;
        Long cursoId = 1L;
        Long instructorId = 1L;

        Curso curso = new Curso();
        curso.setId_curso(cursoId);
        cursoRepository.save(curso);

        Instructor instructor = new Instructor();
        instructor.setIdInstructor(instructorId);
        instructorRepository.save(instructor);

        Clase claseActual = new Clase();
        claseActual.setId(claseId);
        claseActual.setCurso(curso);
        claseActual.setInstructor(instructor);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = dateFormat.parse("01/01/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        claseActual.setFecha(fechaNacimiento);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date horaInicio = null;
        try {
            horaInicio = timeFormat.parse("10:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date horaFin = null;
        try {
            horaFin = timeFormat.parse("11:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        claseActual.setHoraInicio(new java.sql.Time(horaInicio.getTime()));
        claseActual.setHoraFin(new java.sql.Time(horaFin.getTime()));

        claseRepository.save(claseActual);

        Clase claseDetails = new Clase();
        claseDetails.setCurso(curso);
        claseDetails.setInstructor(instructor);
        claseDetails.setFecha(fechaNacimiento);
        claseDetails.setHoraInicio(new java.sql.Time(horaInicio.getTime()));
        claseDetails.setHoraFin(new java.sql.Time(horaFin.getTime()));

        // Ejecutar el método a probar
        Clase updatedClase = claseService.updateClase(claseId, claseDetails);

        // Verificar los resultados
        assertNotNull(updatedClase);
        assertEquals(claseDetails.getFecha(), updatedClase.getFecha());
        assertEquals(claseDetails.getHoraInicio(), updatedClase.getHoraInicio());
        assertEquals(claseDetails.getHoraFin(), updatedClase.getHoraFin());
        assertEquals(curso, updatedClase.getCurso());
        assertEquals(instructor, updatedClase.getInstructor());

        // Verificar que los datos fueron guardados en la base de datos
        Clase foundClase = claseRepository.findById(claseId).orElse(null);
        assertNotNull(foundClase);
        assertEquals(claseDetails.getFecha(), foundClase.getFecha());
        assertEquals(claseDetails.getHoraInicio(), foundClase.getHoraInicio());
        assertEquals(claseDetails.getHoraFin(), foundClase.getHoraFin());
        assertEquals(curso, foundClase.getCurso());
        assertEquals(instructor, foundClase.getInstructor());
    }
}