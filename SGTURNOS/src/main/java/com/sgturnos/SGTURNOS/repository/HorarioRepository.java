package com.sgturnos.SGTURNOS.repository;

import com.sgturnos.SGTURNOS.model.Horario; // Importa tu modelo de Horario
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    Horario findByNombreHorario(String nombreHorario);
}