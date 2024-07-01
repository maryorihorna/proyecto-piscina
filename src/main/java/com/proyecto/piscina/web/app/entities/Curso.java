package com.proyecto.piscina.web.app.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="cursos")
public class Curso {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @Column(name = "nombre", length = 20 )
    private String nombre;

    @Column(name = "descripcion", length = 50 )
    private String descripcion;
    
    @Column(name = "nivel", length = 50 )
    private String nivel;
    
    @Column(name = "cupo_maximo", length = 20 )
    private int cupo_maximo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fecha_inicio;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fecha_fin;

	public Curso() {
	}

	public Curso(Long id_curso, String nombre, String descripcion, String nivel, int cupo_maximo, Date fecha_inicio,
			Date fecha_fin) {
		super();
		this.id_curso = id_curso;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.cupo_maximo = cupo_maximo;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}

	public Long getId_curso() {
		return id_curso;
	}

	public void setId_curso(Long id_curso) {
		this.id_curso = id_curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public int getCupo_maximo() {
		return cupo_maximo;
	}

	public void setCupo_maximo(int cupo_maximo) {
		this.cupo_maximo = cupo_maximo;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
    
}
