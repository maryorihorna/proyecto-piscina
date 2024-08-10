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

    public Long getIdadministrador() {
        return idadministrador;
    }

    public void setIdadministrador(Long idadministrador) {
        this.idadministrador = idadministrador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}
