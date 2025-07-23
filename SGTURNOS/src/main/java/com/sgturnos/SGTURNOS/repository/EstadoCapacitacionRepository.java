package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.EstadoCapacitacion; // Importa tu modelo de EstadoCapacitacion
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCapacitacionRepository extends JpaRepository<EstadoCapacitacion, Integer> {
    EstadoCapacitacion findByNombreEstado(String nombreEstado);
}