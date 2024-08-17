package com.proyecto.piscina.web.app.entities;

import jakarta.persistence.*;
import java.util.Set;

@Table(name = "usuarios")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password", length = 200)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // Aquí guardamos los roles como Strings

    @OneToOne(mappedBy = "usuario")
    private Alumno alumno;

    @OneToOne(mappedBy = "usuario")
    private Administrador administrador;

    // Constructor, getters y setters

    public Usuario() {}

    public Usuario(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idusuario = idUsuario;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Usuario orElseThrow(Object object) {
     
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    
}