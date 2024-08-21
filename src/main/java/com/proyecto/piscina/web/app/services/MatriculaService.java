package com.proyecto.piscina.web.app.services;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Alumno;
import com.proyecto.piscina.web.app.entities.Clase;
import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.respository.AlumnoRepository;
import com.proyecto.piscina.web.app.respository.ClaseRepository;
import com.proyecto.piscina.web.app.respository.MatriculaRepository;

@Service
public class MatriculaService {
	 	@Autowired
	    private MatriculaRepository matriculaRepository;

	    @Autowired
	    private AlumnoRepository alumnoRepository;

	    @Autowired
	    private ClaseRepository claseRepository;

	    public List<Matricula> findAll() {
	        return matriculaRepository.findAll();
	    }

	    public Matricula findById(Long id) {
	        return matriculaRepository.findById(id)
	                .orElseThrow(() -> new IllegalStateException("Matrícula no encontrada con ID: " + id));
	    }

	    public Matricula saveMatricula(Matricula matricula) {
	        Alumno alumno = alumnoRepository.findById(matricula.getAlumno().getIdalumno())
	                .orElseThrow(() -> new IllegalStateException("Alumno no encontrado con ID: " + matricula.getAlumno().getIdalumno()));
	        Clase clase = claseRepository.findById(matricula.getClase().getId())
	                .orElseThrow(() -> new IllegalStateException("Clase no encontrada con ID: " + matricula.getClase().getId()));

	        matricula.setAlumno(alumno);
	        matricula.setClase(clase);

	        // Establecer fecha actual
			matricula.setFechaMatricula(new Date());
	        return matriculaRepository.save(matricula);
	    }

	    public void deleteById(Long id) {
	        boolean existe = matriculaRepository.existsById(id);
	        if (!existe) {
	            throw new IllegalStateException("Matrícula no encontrada con ID: " + id);
	        }
	        matriculaRepository.deleteById(id);
	    }

	    public Matricula update(Long id, Matricula updatedMatricula) {
	        Matricula existingMatricula = findById(id);

	        existingMatricula.setAlumno(updatedMatricula.getAlumno());
	        existingMatricula.setClase(updatedMatricula.getClase());
	        existingMatricula.setFechaMatricula(new Date());
	        existingMatricula.setEstado(updatedMatricula.getEstado());

	        return matriculaRepository.save(existingMatricula);
	    }

		public Optional<Matricula> getMatricula(long id) {
			return matriculaRepository.findById(id);
		}

		public float calcularPorcentajePagado() {
			long totalMatriculas = matriculaRepository.count();
			if (totalMatriculas == 0) return 0; // Evita división por cero
	
			long pagadas = matriculaRepository.countByEstado("Pagado");
			return (pagadas * 100.0f) / totalMatriculas;
		}

	
		public List<Matricula> findByEstado(String estado) {
			return matriculaRepository.findByEstado(estado);
		}

		public Map<String, Long> contarMatriculasPorMes() {
			List<Matricula> matriculas = matriculaRepository.findAll();

			// Utiliza un TreeMap para garantizar el orden cronológico basado en la clave entera
			Map<Integer, Long> matriculasPorMes = matriculas.stream()
				.collect(Collectors.groupingBy(matricula -> {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(matricula.getFechaMatricula());
					int month = calendar.get(Calendar.MONTH) + 1; // Los meses en Calendar van de 0 a 11
					int year = calendar.get(Calendar.YEAR);
					return year * 100 + month; // Esto nos da una clave numérica como 202401 para enero de 2024
				}, TreeMap::new, Collectors.counting()));

			// Convertimos las claves numéricas de nuevo a "MMM-yyyy"
			Map<String, Long> resultadoFinal = new LinkedHashMap<>();
			matriculasPorMes.forEach((key, value) -> {
				int year = key / 100;
				int month = key % 100;
				String monthName = new DateFormatSymbols(new Locale("es", "ES")).getShortMonths()[month - 1];
				String formattedDate = String.format("%s-%d", monthName.substring(0, 1).toUpperCase() + monthName.substring(1), year);
				resultadoFinal.put(formattedDate, value);
			});

			return resultadoFinal;
		}
		
}
