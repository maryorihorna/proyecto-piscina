package com.proyecto.piscina.web.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "password", length = 20)
    private String password;

   @OneToOne(mappedBy = "usuario")
    private Alumno alumno;

    @OneToOne(mappedBy = "usuario")
    private Administrador administrador;
    
    public Usuario() {
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(Long idUsuario) {
        idusuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }  
    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario orElseThrow(Object object) {
     
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
