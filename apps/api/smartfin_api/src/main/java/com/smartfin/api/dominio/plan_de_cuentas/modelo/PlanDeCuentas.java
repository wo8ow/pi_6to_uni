package com.smartfin.api.dominio.plan_de_cuentas.modelo;

import java.util.UUID;

public record PlanDeCuentas(
        UUID planDeCuentasId,
        String codigoCuenta,
        String nombreCuenta,
        String naturaleza,
        String grupoNivel1,
        String grupoNivel2,
        String grupoNivel3,
        boolean esMovimiento,
        boolean activo
) {}
