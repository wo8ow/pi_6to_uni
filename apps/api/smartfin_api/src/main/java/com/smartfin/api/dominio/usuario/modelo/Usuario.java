package com.smartfin.api.dominio.usuario.modelo;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Usuario {

    private final UUID usuarioId;
    private final String correo;
    private final String nombreCompleto;
    private final String claveHash;
    private final boolean activo;
    private final OffsetDateTime creadoEn;
    private final OffsetDateTime actualizadoEn;

    public Usuario(
            UUID usuarioId,
            String correo,
            String nombreCompleto,
            String claveHash,
            boolean activo,
            OffsetDateTime creadoEn,
            OffsetDateTime actualizadoEn
    ) {
        this.usuarioId = usuarioId;
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.claveHash = claveHash;
        this.activo = activo;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public UUID getUsuarioId() { return usuarioId; }
    public String getCorreo() { return correo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getClaveHash() { return claveHash; }
    public boolean isActivo() { return activo; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
}
