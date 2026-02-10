package com.smartfin.api.dominio.usuario.modelo;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Rol {

    private final UUID rolId;
    private final String nombre;
    private final String descripcion;
    private final OffsetDateTime creadoEn;
    private final OffsetDateTime actualizadoEn;

    public Rol(
            UUID rolId,
            String nombre,
            String descripcion,
            OffsetDateTime creadoEn,
            OffsetDateTime actualizadoEn
    ) {
        this.rolId = rolId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public UUID getRolId() { return rolId; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
}
