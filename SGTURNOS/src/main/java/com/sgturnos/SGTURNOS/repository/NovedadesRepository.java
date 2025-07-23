package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Novedades; // Importa tu modelo de Novedades
import com.sgturnos.SGTURNOS.model.Empleado;
import com.sgturnos.SGTURNOS.model.EstadoNovedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovedadesRepository extends JpaRepository<Novedades, Integer> {
    List<Novedades> findByEmpleado(Empleado empleado);
    List<Novedades> findByEstadoNovedad(EstadoNovedad estadoNovedad);
    List<Novedades> findByEmpleadoAndEstadoNovedad(Empleado empleado, EstadoNovedad estadoNovedad);
    // Para los jefes, buscar novedades pendientes de aprobar por ejemplo:
    // List<Novedades> findByEstadoNovedadNombreEstado(String nombreEstado);
}