package com.smartfin.api.aplicacion.balance.comando;

import java.math.BigDecimal;
import java.util.UUID;

public class RegistrarBalanceComando {

    private final UUID empresaId;
    private final UUID periodoId;
    private final String codigoCuenta;
    private final BigDecimal saldo;
    private final String monedaCodigo;
    private final String fuente;

    public RegistrarBalanceComando(UUID empresaId,
                                   UUID periodoId,
                                   String codigoCuenta,
                                   BigDecimal saldo,
                                   String monedaCodigo,
                                   String fuente) {
        this.empresaId = empresaId;
        this.periodoId = periodoId;
        this.codigoCuenta = codigoCuenta;
        this.saldo = saldo;
        this.monedaCodigo = monedaCodigo;
        this.fuente = fuente;
    }

    public UUID getEmpresaId() { return empresaId; }
    public UUID getPeriodoId() { return periodoId; }
    public String getCodigoCuenta() { return codigoCuenta; }
    public BigDecimal getSaldo() { return saldo; }
    public String getMonedaCodigo() { return monedaCodigo; }
    public String getFuente() { return fuente; }
}
