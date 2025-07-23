package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_novedad")
public class TipoNovedad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_novedad")
    private Integer idTipoNovedad;

    @Column(name = "nombre_tipo", nullable = false, unique = true)
    private String nombreTipo;

    // Getters y Setters
    public Integer getIdTipoNovedad() {
        return idTipoNovedad;
    }

    public void setIdTipoNovedad(Integer idTipoNovedad) {
        this.idTipoNovedad = idTipoNovedad;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}