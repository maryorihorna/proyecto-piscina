package com.proyecto.piscina.web.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "administradores")
@Entity
public class Administrador extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idadministrador;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Administrador() {
    }

    // Constructor con todos los atributos incluido usuario para crearlo al mismo junto con el administrador
    public Administrador(String nombre, String apellido, String email, String telefono, Usuario usuario) {
        super(nombre, apellido, email, telefono);
        this.usuario = usuario;
    }
    public Administrador(String nombre, String apellido, String email, String telefono) {
        super(nombre, apellido, email, telefono);
    }

    public Long getIdAdministrador() {
        return idadministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        idadministrador = idAdministrador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
