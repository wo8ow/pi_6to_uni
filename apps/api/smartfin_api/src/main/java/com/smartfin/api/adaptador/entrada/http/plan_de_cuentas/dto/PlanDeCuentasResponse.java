package com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto;

import java.util.UUID;

public record PlanDeCuentasResponse(
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
