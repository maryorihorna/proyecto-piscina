package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piscina.web.app.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long>{

}
