package com.smartfin.api.infraestructura.persistencia.jpa.entidad;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "empresa", schema = "smartfin")
public class EmpresaEntidadJpa {

    @Id
    @Column(name = "empresa_id")
    private UUID empresaId;

    private String identificacion;
    private String nombre;
    private String sector;

    @Column(name = "moneda_codigo")
    private String monedaCodigo;

    private boolean activo;

    @Column(name = "eliminado_en")
    private OffsetDateTime eliminadoEn;

    // getters y setters
    public UUID getEmpresaId() { return empresaId; }
    public void setEmpresaId(UUID empresaId) { this.empresaId = empresaId; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getMonedaCodigo() { return monedaCodigo; }
    public void setMonedaCodigo(String monedaCodigo) { this.monedaCodigo = monedaCodigo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public OffsetDateTime getEliminadoEn() { return eliminadoEn; }
    public void setEliminadoEn(OffsetDateTime eliminadoEn) { this.eliminadoEn = eliminadoEn; }
}
