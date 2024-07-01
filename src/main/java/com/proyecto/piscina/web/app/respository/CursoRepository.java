package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.piscina.web.app.entities.*;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

}
