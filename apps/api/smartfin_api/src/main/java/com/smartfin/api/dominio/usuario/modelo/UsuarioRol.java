package com.smartfin.api.dominio.usuario.modelo;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UsuarioRol {

    private final UUID usuarioRolId;
    private final UUID usuarioId;
    private final UUID rolId;
    private final OffsetDateTime creadoEn;
    private final OffsetDateTime actualizadoEn;

    public UsuarioRol(
            UUID usuarioRolId,
            UUID usuarioId,
            UUID rolId,
            OffsetDateTime creadoEn,
            OffsetDateTime actualizadoEn
    ) {
        this.usuarioRolId = usuarioRolId;
        this.usuarioId = usuarioId;
        this.rolId = rolId;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
    }

    public UUID getUsuarioRolId() { return usuarioRolId; }
    public UUID getUsuarioId() { return usuarioId; }
    public UUID getRolId() { return rolId; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
}
