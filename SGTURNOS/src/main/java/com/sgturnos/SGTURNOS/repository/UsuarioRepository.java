package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Usuario; // Importa tu modelo de Usuario
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// La anotación @Repository es opcional en las interfaces JpaRepository,
// pero es buena práctica para claridad.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // JpaRepository<T, ID>
    // T: El tipo de la entidad (Usuario en este caso)
    // ID: El tipo de la clave primaria de la entidad (Integer para idUsuario)

    // Este método es crucial para Spring Security.
    // Spring Data JPA lo implementará automáticamente para buscar un usuario por su email.
    Usuario findByEmail(String email);

    // Puedes añadir más métodos personalizados si los necesitas, por ejemplo:
    // List<Usuario> findByEnabledTrue();
    // List<Usuario> findByRolNombreRol(String nombreRol);
}