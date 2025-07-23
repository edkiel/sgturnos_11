package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.TemaCapacitacion; // Importa tu modelo de TemaCapacitacion
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaCapacitacionRepository extends JpaRepository<TemaCapacitacion, Integer> {
    TemaCapacitacion findByNombreTema(String nombreTema);
}