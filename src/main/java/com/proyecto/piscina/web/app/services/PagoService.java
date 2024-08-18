package com.proyecto.piscina.web.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.piscina.web.app.entities.Matricula;
import com.proyecto.piscina.web.app.entities.Pago;
import com.proyecto.piscina.web.app.respository.MatriculaRepository;
import com.proyecto.piscina.web.app.respository.PagoRepository;

@Service
public class PagoService {
	@Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public Pago findById(Long id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pago no encontrado con ID: " + id));
    }

    public Pago savePago(Pago pago) {
        Matricula matricula = matriculaRepository.findById(pago.getMatricula().getIdMatricula())
                .orElseThrow(() -> new IllegalStateException("Matrícula no encontrada con ID: " + pago.getMatricula().getIdMatricula()));

        pago.setMatricula(matricula);
        pago.setFecha(new Date()); // Establecer la fecha actual al guardar el pago

        return pagoRepository.save(pago);
    }

    public void deleteById(Long id) {
        boolean existe = pagoRepository.existsById(id);
        if (!existe) {
            throw new IllegalStateException("Pago no encontrado con ID: " + id);
        }
        pagoRepository.deleteById(id);
    }

    public Pago update(Long id, Pago updatedPago) {
        Pago existingPago = findById(id);

        existingPago.setMatricula(updatedPago.getMatricula());
        existingPago.setFecha(new Date()); // Actualizar la fecha al modificar el pago
        existingPago.setMonto(updatedPago.getMonto());
        existingPago.setMetodoPago(updatedPago.getMetodoPago());

        return pagoRepository.save(existingPago);
    }

    public Optional<Pago> getPago(long id) {
        return pagoRepository.findById(id);
    }

    public Float calcularSumaTotalMontos() {
        return pagoRepository.sumaTotalMontos();
    }
}
