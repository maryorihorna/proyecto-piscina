package com.proyecto.piscina.web.app.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.piscina.web.app.entities.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long>{

    @Query("SELECT SUM(p.monto) FROM Pago p")
    Float sumaTotalMontos();
}
