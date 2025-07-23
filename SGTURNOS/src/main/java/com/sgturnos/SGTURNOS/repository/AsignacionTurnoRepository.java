package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.AsignacionTurno; // Importa tu modelo de AsignacionTurno
import com.sgturnos.SGTURNOS.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsignacionTurnoRepository extends JpaRepository<AsignacionTurno, Integer> {
    // Métodos personalizados para buscar asignaciones de turno
    List<AsignacionTurno> findByEmpleado(Empleado empleado);
    List<AsignacionTurno> findByEmpleadoAndFechaInicioBetween(Empleado empleado, LocalDate fechaInicio, LocalDate fechaFin);
    List<AsignacionTurno> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
}