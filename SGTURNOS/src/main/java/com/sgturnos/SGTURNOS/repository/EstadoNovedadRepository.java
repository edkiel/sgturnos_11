package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.EstadoNovedad; // Importa tu modelo de EstadoNovedad
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoNovedadRepository extends JpaRepository<EstadoNovedad, Integer> {
    EstadoNovedad findByNombreEstado(String nombreEstado);
}