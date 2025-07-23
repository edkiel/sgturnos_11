package com.sgturnos.SGTURNOS.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "novedades")
public class Novedades implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_novedad")
    private Integer idNovedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_novedad", nullable = false)
    private TipoNovedad tipoNovedad;

    @Column(name = "fecha_novedad", nullable = false)
    private LocalDate fechaNovedad;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_novedad", nullable = false)
    private EstadoNovedad estadoNovedad;

    @Column(name = "fecha_solicitud", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprobado_por_usuario_id")
    private Usuario aprobadoPorUsuario;

    @Column(name = "fecha_aprobacion")
    private LocalDateTime fechaAprobacion;

    // Getters y Setters
    public Integer getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(Integer idNovedad) {
        this.idNovedad = idNovedad;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoNovedad getTipoNovedad() {
        return tipoNovedad;
    }

    public void setTipoNovedad(TipoNovedad tipoNovedad) {
        this.tipoNovedad = tipoNovedad;
    }

    public LocalDate getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(LocalDate fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoNovedad getEstadoNovedad() {
        return estadoNovedad;
    }

    public void setEstadoNovedad(EstadoNovedad estadoNovedad) {
        this.estadoNovedad = estadoNovedad;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Usuario getAprobadoPorUsuario() {
        return aprobadoPorUsuario;
    }

    public void setAprobadoPorUsuario(Usuario aprobadoPorUsuario) {
        this.aprobadoPorUsuario = aprobadoPorUsuario;
    }

    public LocalDateTime getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDateTime fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }
}