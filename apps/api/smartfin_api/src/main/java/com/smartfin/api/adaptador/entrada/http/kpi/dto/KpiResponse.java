package com.smartfin.api.adaptador.entrada.http.kpi.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class KpiResponse {
    private UUID kpiId;
    private String codigoKpi;
    private String nombreKpi;
    private String descripcion;
    private String unidad;
    private boolean activo;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

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

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
