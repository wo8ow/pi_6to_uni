package com.smartfin.api.dominio.balance.modelo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Balance {

    private UUID balanceId;
    private UUID empresaId;
    private UUID periodoId;
    private String codigoCuenta;
    private BigDecimal saldo;
    private String monedaCodigo;
    private String fuente;
    private OffsetDateTime creadoEn;
    private OffsetDateTime actualizadoEn;

    public Balance() {}

    public Balance(UUID balanceId,
                   UUID empresaId,
                   UUID periodoId,
                   String codigoCuenta,
                   BigDecimal saldo,
                   String monedaCodigo,
                   String fuente,
                   OffsetDateTime creadoEn,
                   OffsetDateTime actualizadoEn) {
        this.balanceId = balanceId;
        this.empresaId = empresaId;
        this.periodoId = periodoId;
        this.codigoCuenta = codigoCuenta;
        this.saldo = saldo;
        this.monedaCodigo = monedaCodigo;
        this.fuente = fuente;
        this.creadoEn = creadoEn;
        this.actualizadoEn = actualizadoEn;
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
