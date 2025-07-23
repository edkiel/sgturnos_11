package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estado_novedad")
public class EstadoNovedad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_novedad")
    private Integer idEstadoNovedad;

    @Column(name = "nombre_estado", nullable = false, unique = true)
    private String nombreEstado;

    // Getters y Setters
    public Integer getIdEstadoNovedad() {
        return idEstadoNovedad;
    }

    public void setIdEstadoNovedad(Integer idEstadoNovedad) {
        this.idEstadoNovedad = idEstadoNovedad;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}