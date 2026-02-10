package com.smartfin.api.adaptador.entrada.http.balance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class RegistrarBalanceRequest {

    @NotNull
    private UUID empresaId;

    @NotNull
    private UUID periodoId;

    @NotBlank
    private String codigoCuenta;

    @NotNull
    private BigDecimal saldo;

    private String monedaCodigo;
    private String fuente;

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
}
