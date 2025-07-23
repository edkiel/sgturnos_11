package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estado_capacitacion")
public class EstadoCapacitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_capacitacion")
    private Integer idEstadoCapacitacion;

    @Column(name = "nombre_estado", nullable = false, unique = true)
    private String nombreEstado;

    // Getters y Setters
    public Integer getIdEstadoCapacitacion() {
        return idEstadoCapacitacion;
    }

    public void setIdEstadoCapacitacion(Integer idEstadoCapacitacion) {
        this.idEstadoCapacitacion = idEstadoCapacitacion;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}