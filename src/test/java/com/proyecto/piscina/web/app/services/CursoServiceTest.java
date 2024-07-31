package com.proyecto.piscina.web.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.respository.CursoRepository;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void testSaveCurso() {
        // Configurar el objeto Curso
        Curso curso = new Curso();
        curso.setNombre("Curso de Natación");
        curso.setDescripcion("Aprender a nadar");
        curso.setNivel("Principiante");
        curso.setCupo_maximo(20);

        // Configurar el comportamiento del mock
        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        // Llamar al método que se está probando
        Curso savedCurso = cursoService.saveCurso(curso);

        // Verificar que el método save del repositorio fue llamado
        verify(cursoRepository).save(curso);

        // Verificar que el curso guardado es el esperado
        assertEquals("Curso de Natación", savedCurso.getNombre());
        assertEquals("Aprender a nadar", savedCurso.getDescripcion());
        assertEquals("Principiante", savedCurso.getNivel());
        assertEquals(20, savedCurso.getCupo_maximo());
    }
}