package com.smartfin.api.dominio.balance.puerto;

import com.smartfin.api.dominio.balance.modelo.Balance;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceRepositorio {

    Balance guardar(Balance balance);

    Optional<Balance> obtenerPorId(UUID balanceId);

    Optional<Balance> buscarPorEmpresaPeriodoCuenta(UUID empresaId, UUID periodoId, String codigoCuenta);

    List<Balance> listar(UUID empresaId, UUID periodoId);
}
