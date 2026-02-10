package com.smartfin.api.adaptador.entrada.http.kpi.dto;

public class KpiCrearRequest {
    private String codigoKpi;
    private String nombreKpi;
    private String descripcion;
    private String unidad;

    public String getCodigoKpi() { return codigoKpi; }
    public void setCodigoKpi(String codigoKpi) { this.codigoKpi = codigoKpi; }

    public String getNombreKpi() { return nombreKpi; }
    public void setNombreKpi(String nombreKpi) { this.nombreKpi = nombreKpi; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
}
