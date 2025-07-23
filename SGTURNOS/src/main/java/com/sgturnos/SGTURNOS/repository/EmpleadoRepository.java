package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Empleado; // Importa tu modelo de Empleado
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Empleado findByDocumentoIdentidad(String documentoIdentidad);
    // Puedes añadir más métodos de búsqueda si los necesitas, por ejemplo:
    // List<Empleado> findByDepartamentoNombreDepartamento(String nombreDepartamento);
}