package com.smartfin.api.aplicacion.balance.caso_uso;

import com.smartfin.api.dominio.balance.modelo.Balance;
import com.smartfin.api.dominio.balance.puerto.BalanceRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultarBalanceCasoUso {

    private final BalanceRepositorio balanceRepositorio;

    public ConsultarBalanceCasoUso(BalanceRepositorio balanceRepositorio) {
        this.balanceRepositorio = balanceRepositorio;
    }

    public List<Balance> listar(UUID empresaId, UUID periodoId) {
        return balanceRepositorio.listar(empresaId, periodoId);
    }

    public Balance obtenerPorId(UUID balanceId) {
        return balanceRepositorio.obtenerPorId(balanceId)
                .orElseThrow(() -> new IllegalArgumentException("Balance no encontrado: " + balanceId));
    }
}
