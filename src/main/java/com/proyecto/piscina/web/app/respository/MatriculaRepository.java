package com.proyecto.piscina.web.app.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piscina.web.app.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long>{
    long countByEstado(String estado);
    List<Matricula> findByEstado(String estado);
}
