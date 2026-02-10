package com.smartfin.api.dominio.plan_de_cuentas.puerto;

import com.smartfin.api.dominio.plan_de_cuentas.modelo.PlanDeCuentas;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlanDeCuentasRepositorio {
    PlanDeCuentas guardar(PlanDeCuentas planDeCuentas);
    Optional<PlanDeCuentas> buscarPorId(UUID planDeCuentasId);
    Optional<PlanDeCuentas> buscarPorCodigoCuenta(String codigoCuenta);
    List<PlanDeCuentas> listar();
    boolean existePorCodigoCuenta(String codigoCuenta);
}
