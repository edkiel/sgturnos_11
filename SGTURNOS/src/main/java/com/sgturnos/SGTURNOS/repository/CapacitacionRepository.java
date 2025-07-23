package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Capacitacion; // Importa tu modelo de Capacitacion
import com.sgturnos.SGTURNOS.model.TemaCapacitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CapacitacionRepository extends JpaRepository<Capacitacion, Integer> {
    List<Capacitacion> findByFechaCapacitacion(LocalDate fecha);
    List<Capacitacion> findByTemaCapacitacion(TemaCapacitacion tema);
}