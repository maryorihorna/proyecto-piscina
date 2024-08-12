package com.proyecto.piscina.web.app.entities;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.*;

@Table(name = "clase")
@Entity
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    private Curso curso;

    @ManyToOne
    private Instructor instructor;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    @Column(name = "horaInicio", nullable = false)
    private Time horaInicio;

    @Temporal(TemporalType.TIME)
    @Column(name = "horaFin", nullable = false)
    private Time horaFin;
    
    public Clase() {
    }

    public Clase(long id, Curso curso, Instructor instructor, Date fecha, Time horaInicio, Time horaFin) {
        this.id = id;
        this.curso = curso;
        this.instructor = instructor;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    
}
