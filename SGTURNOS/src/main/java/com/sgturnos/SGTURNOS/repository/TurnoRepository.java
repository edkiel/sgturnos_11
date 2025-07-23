package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Turno; // Importa tu modelo de Turno
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    // Puedes añadir métodos de búsqueda si necesitas encontrar turnos por descripción, horario, etc.
    // Turno findByDescripcion(String descripcion);
}