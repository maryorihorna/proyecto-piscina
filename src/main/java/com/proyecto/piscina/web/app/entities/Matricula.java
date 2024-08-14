package com.proyecto.piscina.web.app.entities;

import java.util.Date;

import jakarta.persistence.*;

@Table(name="matriculas")
@Entity
public class Matricula {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long idMatricula;

    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "idalumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_clase", referencedColumnName = "id")
    private Clase clase;

    @Column(name = "fecha_matricula")
    private Date fechaMatricula;

    @Column(name = "estado")
    private String estado;
	
	
	public Matricula() {
	}


	public Matricula(Long idMatricula, Alumno alumno, Clase clase, Date fechaMatricula, String estado) {
		this.idMatricula = idMatricula;
		this.alumno = alumno;
		this.clase = clase;
		this.fechaMatricula = fechaMatricula;
		this.estado = estado;
	}


	public Long getIdMatricula() {
		return idMatricula;
	}


	public void setIdMatricula(Long idMatricula) {
		this.idMatricula = idMatricula;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Clase getClase() {
		return clase;
	}


	public void setClase(Clase clase) {
		this.clase = clase;
	}


	public Date getFechaMatricula() {
		return fechaMatricula;
	}


	public void setFechaMatricula(Date fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
