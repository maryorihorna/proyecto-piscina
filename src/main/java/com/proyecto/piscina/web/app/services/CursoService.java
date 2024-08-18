package com.proyecto.piscina.web.app.services;

import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Curso;
import com.proyecto.piscina.web.app.respository.CursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
	 
	 private final CursoRepository cursoRepository;

	 
	 public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	 }

	 public Curso saveCurso(Curso curso) {
		return cursoRepository.save(curso);
	 }

	 public Curso updateCurso(Long id, Curso cursoDetails) {
		Curso cursoActual = cursoRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("El curso con id " + id + " no existe"));
		cursoActual.setNombre(cursoDetails.getNombre());
		cursoActual.setDescripcion(cursoDetails.getDescripcion());
		cursoActual.setNivel(cursoDetails.getNivel());
		cursoActual.setCupo_maximo(cursoDetails.getCupo_maximo());
		cursoActual.setFecha_inicio(cursoDetails.getFecha_inicio());
		cursoActual.setFecha_fin(cursoDetails.getFecha_fin());
		return cursoRepository.save(cursoActual);
	 }

		public Curso getCurso(Long id) {
			return cursoRepository.findById(id).orElseThrow(() -> new IllegalStateException("El curso con id " + id + " no existe"));
		}

	    public List<Curso> getAllCursos() {
	        return cursoRepository.findAll();
	    }

	    public void deleteCurso(Long id) {
	        boolean existe = cursoRepository.existsById(id);
	        if (!existe) {
	            throw new IllegalStateException("El curso con id " + id + " no existe");
	        }
	        cursoRepository.deleteById(id);
	    }

	    public Optional<Curso> getCurso(long id) {
	        return cursoRepository.findById(id);
	    }
		
}
