package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(schema = "smartfin", name = "usuario")
public class UsuarioEntidadJpa {

    @Id
    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "correo", nullable = false, length = 254, unique = true)
    private String correo;

    @Column(name = "nombre_completo", nullable = false, length = 200)
    private String nombreCompleto;

    @Column(name = "clave_hash", nullable = false, length = 200)
    private String claveHash;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected UsuarioEntidadJpa() {}

    public UUID getUsuarioId() { return usuarioId; }
    public String getCorreo() { return correo; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getClaveHash() { return claveHash; }
    public Boolean getActivo() { return activo; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }

    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setClaveHash(String claveHash) { this.claveHash = claveHash; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
