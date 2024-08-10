package com.proyecto.piscina.web.app.entities;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Table(name = "alumnos")
@Entity
public class Alumno extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idalumno;

    @Column(name = "direccion", length = 100)
    private String direccion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

    public Alumno() {
    }

    public Long getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(Long idalumno) {
        this.idalumno = idalumno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



}
