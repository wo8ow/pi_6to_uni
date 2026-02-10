package com.smartfin.api.infraestructura.persistencia.jpa.entidad;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "kpi", schema = "smartfin")
public class KpiEntidadJpa {

    @Id
    @Column(name = "kpi_id", nullable = false)
    private UUID kpiId;

    @Column(name = "codigo_kpi", nullable = false, length = 60)
    private String codigoKpi;

    @Column(name = "nombre_kpi", nullable = false, length = 200)
    private String nombreKpi;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "unidad", nullable = false, length = 40)
    private String unidad;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "eliminado_en")
    private OffsetDateTime eliminadoEn;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    public KpiEntidadJpa() {}

    public UUID getKpiId() { return kpiId; }
    public void setKpiId(UUID kpiId) { this.kpiId = kpiId; }

    public String getCodigoKpi() { return codigoKpi; }
    public void setCodigoKpi(String codigoKpi) { this.codigoKpi = codigoKpi; }

    public String getNombreKpi() { return nombreKpi; }
    public void setNombreKpi(String nombreKpi) { this.nombreKpi = nombreKpi; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public OffsetDateTime getEliminadoEn() { return eliminadoEn; }
    public void setEliminadoEn(OffsetDateTime eliminadoEn) { this.eliminadoEn = eliminadoEn; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
