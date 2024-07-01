package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piscina.web.app.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}
