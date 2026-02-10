package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "balance", schema = "smartfin",
        uniqueConstraints = @UniqueConstraint(name = "ux_balance_unico",
                columnNames = {"empresa_id", "periodo_id", "codigo_cuenta"}))
public class BalanceEntidadJpa {

    @Id
    @Column(name = "balance_id", nullable = false)
    private UUID balanceId;

    @Column(name = "empresa_id", nullable = false)
    private UUID empresaId;

    @Column(name = "periodo_id", nullable = false)
    private UUID periodoId;

    @Column(name = "codigo_cuenta", nullable = false, length = 40)
    private String codigoCuenta;

    @Column(name = "saldo", nullable = false, precision = 18, scale = 2)
    private BigDecimal saldo;

    @Column(name = "moneda_codigo", nullable = false, length = 10)
    private String monedaCodigo;

    @Column(name = "fuente", length = 120)
    private String fuente;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    public BalanceEntidadJpa() {}

    @PrePersist
    public void prePersist() {
        if (balanceId == null) balanceId = UUID.randomUUID();
        if (creadoEn == null) creadoEn = OffsetDateTime.now();
        if (actualizadoEn == null) actualizadoEn = OffsetDateTime.now();
        if (monedaCodigo == null) monedaCodigo = "USD";
        if (saldo == null) saldo = BigDecimal.ZERO;
    }

    @PreUpdate
    public void preUpdate() {
        actualizadoEn = OffsetDateTime.now();
        if (monedaCodigo == null) monedaCodigo = "USD";
        if (saldo == null) saldo = BigDecimal.ZERO;
    }

    public UUID getBalanceId() { return balanceId; }
    public void setBalanceId(UUID balanceId) { this.balanceId = balanceId; }

    public UUID getEmpresaId() { return empresaId; }
    public void setEmpresaId(UUID empresaId) { this.empresaId = empresaId; }

    public UUID getPeriodoId() { return periodoId; }
    public void setPeriodoId(UUID periodoId) { this.periodoId = periodoId; }

    public String getCodigoCuenta() { return codigoCuenta; }
    public void setCodigoCuenta(String codigoCuenta) { this.codigoCuenta = codigoCuenta; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public String getMonedaCodigo() { return monedaCodigo; }
    public void setMonedaCodigo(String monedaCodigo) { this.monedaCodigo = monedaCodigo; }

    public String getFuente() { return fuente; }
    public void setFuente(String fuente) { this.fuente = fuente; }

    public OffsetDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(OffsetDateTime creadoEn) { this.creadoEn = creadoEn; }

    public OffsetDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(OffsetDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}
