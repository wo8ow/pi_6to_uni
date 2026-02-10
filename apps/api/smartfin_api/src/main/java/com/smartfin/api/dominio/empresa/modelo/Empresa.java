package com.smartfin.api.dominio.empresa.modelo;

import java.util.UUID;

public class Empresa {

    private UUID empresaId;
    private String identificacion;
    private String nombre;
    private String sector;
    private String monedaCodigo;
    private boolean activo;

    public Empresa(UUID empresaId, String identificacion, String nombre,
                   String sector, String monedaCodigo, boolean activo) {
        this.empresaId = empresaId;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.sector = sector;
        this.monedaCodigo = monedaCodigo;
        this.activo = activo;
    }

    public UUID getEmpresaId() {
        return empresaId;
    }

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

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }
}
