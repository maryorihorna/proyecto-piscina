package com.proyecto.piscina.web.app.services;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.AlumnoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AlumnoServiceIntegrationDatabaseTest {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        // Configuración adicional si es necesario
    }

    @Test
    public void testAlumnoDatabase() {
        // Crear un usuario y un alumno
        Usuario usuario = new Usuario();
        usuario.setUsername("carlos");
        usuario.setPassword("password123"); // Contraseña sin encriptar

        Alumno alumno = new Alumno();
        alumno.setNombre("Carlos");
        alumno.setUsuario(usuario);
        alumno.setApellido("Perez");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = dateFormat.parse("01/01/2000");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        alumno.setFecha_nacimiento(fechaNacimiento);
        alumno.setEmail("carlos@gmail.com");
        alumno.setDireccion("Calle 123");
        alumno.setFecha_nacimiento(fechaNacimiento);

        // Guardar el alumno
        Alumno savedAlumno = alumnoService.saveAlumno(alumno);

        // Verificar que el alumno fue guardado
        assertNotNull(savedAlumno);
        assertNotNull(savedAlumno.getUsuario());

        // Verificar que la contraseña fue encriptada correctamente
        assertTrue(passwordEncoder.matches("password123", savedAlumno.getUsuario().getPassword()));

        // Verificar que los datos fueron guardados en la base de datos
        Alumno foundAlumno = alumnoRepository.findById(savedAlumno.getIdalumno()).orElse(null);
        assertNotNull(foundAlumno);
        assertEquals("Carlos", foundAlumno.getNombre());
    }
}