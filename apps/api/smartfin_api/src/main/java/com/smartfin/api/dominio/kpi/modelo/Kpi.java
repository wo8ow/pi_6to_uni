package com.smartfin.api.dominio.kpi.modelo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Kpi {
    private UUID kpiId;
    private String codigoKpi;
    private String nombreKpi;
    private String descripcion;
    private String unidad;
    private boolean activo;
    private OffsetDateTime eliminadoEn;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    public Kpi() {}

    public Kpi(UUID kpiId, String codigoKpi, String nombreKpi, String descripcion, String unidad,
               boolean activo, OffsetDateTime eliminadoEn, OffsetDateTime creadoEn, OffsetDateTime actualizadoEn) {
        this.kpiId = kpiId;
        this.codigoKpi = codigoKpi;
        this.nombreKpi = nombreKpi;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.activo = activo;
        this.eliminadoEn = eliminadoEn;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

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
