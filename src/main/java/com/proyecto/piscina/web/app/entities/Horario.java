package com.proyecto.piscina.web.app.entities;

import jakarta.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "horarios")
public class Horario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_horario;

	@ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "IdInstructor")
    private Instructor instructor;

    @Column(name = "dia_semana", length = 20, nullable = false)
    private String dia_semana;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio", nullable = false)
    private Time hora_inicio;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_fin", nullable = false)
    private Time hora_fin;

    public Horario() {
	}
    
    public Horario(Long id_horario, Instructor instructor, String dia_semana, Time hora_inicio, Time hora_fin) {
		super();
		this.id_horario = id_horario;
		this.instructor = instructor;
		this.dia_semana = dia_semana;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
	}

	public Long getId_horario() {
		return id_horario;
	}

	public void setId_horario(Long id_horario) {
		this.id_horario = id_horario;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Time getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}
    
    

	

    
	
	
}
