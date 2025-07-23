package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "capacitacion")
public class Capacitacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tema_capacitacion", nullable = false)
    private TemaCapacitacion temaCapacitacion;

    @Column(name = "fecha_capacitacion", nullable = false)
    private LocalDate fechaCapacitacion;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_capacitacion", nullable = false)
    private EstadoCapacitacion estadoCapacitacion;

    // Getters y Setters
    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public TemaCapacitacion getTemaCapacitacion() {
        return temaCapacitacion;
    }

    public void setTemaCapacitacion(TemaCapacitacion temaCapacitacion) {
        this.temaCapacitacion = temaCapacitacion;
    }

    public LocalDate getFechaCapacitacion() {
        return fechaCapacitacion;
    }

    public void setFechaCapacitacion(LocalDate fechaCapacitacion) {
        this.fechaCapacitacion = fechaCapacitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoCapacitacion getEstadoCapacitacion() {
        return estadoCapacitacion;
    }

    public void setEstadoCapacitacion(EstadoCapacitacion estadoCapacitacion) {
        this.estadoCapacitacion = estadoCapacitacion;
    }
}