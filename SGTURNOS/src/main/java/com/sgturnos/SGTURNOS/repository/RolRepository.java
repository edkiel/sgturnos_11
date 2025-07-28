package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> { // Asumiendo que el ID de Rol es Integer
    // Puedes añadir métodos específicos si los necesitas, ej:
    // Rol findByNombreRol(String nombreRol);
}