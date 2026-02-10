package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "periodo", schema = "smartfin")
public class PeriodoEntidadJpa {

    @Id
    @Column(name = "periodo_id", nullable = false)
    private UUID periodoId;

    @Column(name = "periodo_codigo", nullable = false, length = 16)
    private String periodoCodigo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "tipo_periodo", nullable = false, length = 20)
    private String tipoPeriodo;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @Column(name = "eliminado_en")
    private OffsetDateTime eliminadoEn;

    // Si en DB existen y tienen DEFAULT/trigger, esto evita inserts con null
    @Column(name = "creado_en", insertable = false, updatable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", insertable = false, updatable = false)
    private OffsetDateTime actualizadoEn;

    protected PeriodoEntidadJpa() {}

    public PeriodoEntidadJpa(UUID periodoId, String periodoCodigo, LocalDate fechaInicio, LocalDate fechaFin, String tipoPeriodo) {
        this.periodoId = periodoId;
        this.periodoCodigo = periodoCodigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoPeriodo = tipoPeriodo;
        this.activo = true;
        this.eliminadoEn = null;
    }

    public UUID getPeriodoId() { return periodoId; }
    public String getPeriodoCodigo() { return periodoCodigo; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getTipoPeriodo() { return tipoPeriodo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public OffsetDateTime getEliminadoEn() { return eliminadoEn; }
    public void setEliminadoEn(OffsetDateTime eliminadoEn) { this.eliminadoEn = eliminadoEn; }
}
