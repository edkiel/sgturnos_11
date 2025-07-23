package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Departamento; // Importa tu modelo de Departamento
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    // JpaRepository<T, ID>
    // T: El tipo de la entidad (Departamento en este caso)
    // ID: El tipo de la clave primaria de la entidad (Integer para idDepartamento)

    // Spring Data JPA provee automáticamente métodos como save(), findById(), findAll(), delete()
    // Si necesitas métodos de búsqueda específicos (ej. findByNombreDepartamento), los declares aquí:
    Departamento findByNombreDepartamento(String nombreDepartamento);
}