package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(schema = "smartfin", name = "rol")
public class RolEntidadJpa {

    @Id
    @Column(name = "rol_id", nullable = false)
    private UUID rolId;

    @Column(name = "nombre", nullable = false, length = 80, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected RolEntidadJpa() {}

    public UUID getRolId() { return rolId; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
}
