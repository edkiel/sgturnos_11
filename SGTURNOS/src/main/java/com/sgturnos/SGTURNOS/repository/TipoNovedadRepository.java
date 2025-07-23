package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.TipoNovedad; // Importa tu modelo de TipoNovedad
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNovedadRepository extends JpaRepository<TipoNovedad, Integer> {
    TipoNovedad findByNombreTipo(String nombreTipo);
}