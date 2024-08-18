package com.proyecto.piscina.web.app.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "instructores")
@Entity
public class Instructor extends Persona{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdInstructor;

    public Instructor() {
    }

    public Long getIdInstructor() {
        return IdInstructor;
    }

    public void setIdInstructor(Long idInstructor) {
        IdInstructor = idInstructor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor curso = (Instructor) o;
        return Objects.equals(IdInstructor, curso.IdInstructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdInstructor);
    }
    
}
