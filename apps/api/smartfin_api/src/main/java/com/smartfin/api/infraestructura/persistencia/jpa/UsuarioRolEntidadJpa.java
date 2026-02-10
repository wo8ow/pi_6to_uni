package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(schema = "smartfin", name = "usuario_rol")
public class UsuarioRolEntidadJpa {

    @Id
    @Column(name = "usuario_rol_id", nullable = false)
    private UUID usuarioRolId;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "rol_id", nullable = false)
    private UUID rolId;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected UsuarioRolEntidadJpa() {}

    public UUID getUsuarioRolId() { return usuarioRolId; }
    public UUID getUsuarioId() { return usuarioId; }
    public UUID getRolId() { return rolId; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }

    public void setUsuarioRolId(UUID usuarioRolId) { this.usuarioRolId = usuarioRolId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public void setRolId(UUID rolId) { this.rolId = rolId; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
