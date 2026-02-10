package com.smartfin.api.adaptador.entrada.http.empresa.dto;

public class EmpresaCrearRequest {

    private String identificacion;
    private String nombre;
    private String sector;
    private String monedaCodigo;

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSector() {
        return sector;
    }

    public String getMonedaCodigo() {
        return monedaCodigo;
    }
}
