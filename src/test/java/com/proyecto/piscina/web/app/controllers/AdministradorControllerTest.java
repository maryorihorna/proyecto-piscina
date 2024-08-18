
package com.proyecto.piscina.web.app.controllers;

import com.proyecto.piscina.web.app.entities.Administrador;
import com.proyecto.piscina.web.app.entities.Usuario;
import com.proyecto.piscina.web.app.respository.AdministradorRespository;
import com.proyecto.piscina.web.app.respository.UsuarioRepository;
import com.proyecto.piscina.web.app.services.AdministradorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdministradorControllerTest {

    @Autowired
    private AdministradorService administradorService;

    @MockBean
    private AdministradorRespository administradorRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveAdministrador() {
        Usuario usuario = new Usuario();
        usuario.setPassword("plainPassword");

        Administrador administrador = new Administrador();
        administrador.setUsuario(usuario);

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(administradorRepository.save(any(Administrador.class))).thenReturn(administrador);

        Administrador savedAdministrador = administradorService.saveAdministrador(administrador);

        assertNotNull(savedAdministrador);
        assertEquals("encodedPassword", savedAdministrador.getUsuario().getPassword());
    }

    @Test
    public void testUpdateAdministrador() {
        long id = 1L;
        Administrador existingAdministrador = new Administrador();
        existingAdministrador.setIdadministrador(id);

        Administrador updatedAdministrador = new Administrador();
        updatedAdministrador.setNombre("Nuevo Nombre");

        when(administradorRepository.findById(id)).thenReturn(Optional.of(existingAdministrador));
        when(administradorRepository.save(any(Administrador.class))).thenReturn(existingAdministrador);

        Administrador result = administradorService.updateAdministrador(id, updatedAdministrador);

        assertNotNull(result);
        assertEquals("Nuevo Nombre", result.getNombre());
    }


    @Test
    public void testDeleteAdministrador() {
        long id = 1L;

        when(administradorRepository.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(administradorRepository).deleteById(id);

        assertDoesNotThrow(() -> administradorService.deleteAdministrador(id));
    }
}