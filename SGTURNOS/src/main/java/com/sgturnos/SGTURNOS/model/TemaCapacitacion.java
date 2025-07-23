package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tema_capacitacion")
public class TemaCapacitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tema_capacitacion")
    private Integer idTemaCapacitacion;

    @Column(name = "nombre_tema", nullable = false, unique = true)
    private String nombreTema;

    // Getters y Setters
    public Integer getIdTemaCapacitacion() {
        return idTemaCapacitacion;
    }

    public void setIdTemaCapacitacion(Integer idTemaCapacitacion) {
        this.idTemaCapacitacion = idTemaCapacitacion;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
}