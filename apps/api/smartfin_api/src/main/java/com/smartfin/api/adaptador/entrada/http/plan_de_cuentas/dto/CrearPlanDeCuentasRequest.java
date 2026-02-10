package com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto;

public record CrearPlanDeCuentasRequest(
        String codigoCuenta,
        String nombreCuenta,
        String naturaleza,
        String grupoNivel1,
        String grupoNivel2,
        String grupoNivel3,
        Boolean esMovimiento,
        Boolean activo
) {}
