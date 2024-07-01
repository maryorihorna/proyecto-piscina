package com.proyecto.piscina.web.app.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pago {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @ManyToOne
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    private Matricula matricula;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "monto")
    private Float monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    public Pago() {
	}

	public Pago(Long idPago, Matricula matricula, Date fecha, Float monto, String metodoPago) {
		this.matricula = matricula;
		this.fecha = fecha;
		this.monto = monto;
		this.metodoPago = metodoPago;
	}

	public Long getIdPago() {
		return idPago;
	}

	public void setIdPago(Long idPago) {
		this.idPago = idPago;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
    
    

}
