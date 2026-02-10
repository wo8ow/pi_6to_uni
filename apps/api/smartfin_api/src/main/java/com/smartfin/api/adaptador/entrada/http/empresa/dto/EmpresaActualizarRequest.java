package com.smartfin.api.adaptador.entrada.http.empresa.dto;

public class EmpresaActualizarRequest {

    private String nombre;
    private String sector;
    private String monedaCodigo;
    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    public String getMonedaCodigo() {
        return monedaCodigo;
    }

    public boolean isActivo() {
        return activo;
    }
}
