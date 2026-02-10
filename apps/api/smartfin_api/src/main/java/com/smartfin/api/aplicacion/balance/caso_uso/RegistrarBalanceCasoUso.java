package com.smartfin.api.aplicacion.balance.caso_uso;

import com.smartfin.api.aplicacion.balance.comando.RegistrarBalanceComando;
import com.smartfin.api.dominio.balance.modelo.Balance;
import com.smartfin.api.dominio.balance.puerto.BalanceRepositorio;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class RegistrarBalanceCasoUso {

    private final BalanceRepositorio balanceRepositorio;

    public RegistrarBalanceCasoUso(BalanceRepositorio balanceRepositorio) {
        this.balanceRepositorio = balanceRepositorio;
    }

    /**
     * Upsert por (empresaId, periodoId, codigoCuenta).
     */
    public Balance ejecutar(RegistrarBalanceComando comando) {

        Balance balance = balanceRepositorio
                .buscarPorEmpresaPeriodoCuenta(comando.getEmpresaId(), comando.getPeriodoId(), comando.getCodigoCuenta())
                .orElseGet(Balance::new);

        balance.setEmpresaId(comando.getEmpresaId());
        balance.setPeriodoId(comando.getPeriodoId());
        balance.setCodigoCuenta(comando.getCodigoCuenta());
        balance.setSaldo(comando.getSaldo());
        balance.setMonedaCodigo(comando.getMonedaCodigo() == null ? "USD" : comando.getMonedaCodigo());
        balance.setFuente(comando.getFuente());

        // timestamps: si no vienen de BD, al menos no rompen nada
        if (balance.getCreadoEn() == null) {
            balance.setCreadoEn(OffsetDateTime.now());
        }
        balance.setActualizadoEn(OffsetDateTime.now());

        return balanceRepositorio.guardar(balance);
    }
}
