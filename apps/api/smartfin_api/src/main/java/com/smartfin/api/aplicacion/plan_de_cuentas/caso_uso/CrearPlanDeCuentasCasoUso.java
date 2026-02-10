package com.smartfin.api.aplicacion.plan_de_cuentas.caso_uso;

import com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto.CrearPlanDeCuentasRequest;
import com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto.PlanDeCuentasResponse;
import com.smartfin.api.dominio.plan_de_cuentas.modelo.PlanDeCuentas;
import com.smartfin.api.dominio.plan_de_cuentas.puerto.PlanDeCuentasRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearPlanDeCuentasCasoUso {

    private final PlanDeCuentasRepositorio planDeCuentasRepositorio;

    public CrearPlanDeCuentasCasoUso(PlanDeCuentasRepositorio planDeCuentasRepositorio) {
        this.planDeCuentasRepositorio = planDeCuentasRepositorio;
    }

    public PlanDeCuentasResponse ejecutar(CrearPlanDeCuentasRequest solicitud) {
        if (solicitud.codigoCuenta() == null || solicitud.codigoCuenta().isBlank()) {
            throw new IllegalArgumentException("El codigoCuenta es obligatorio");
        }
        if (solicitud.nombreCuenta() == null || solicitud.nombreCuenta().isBlank()) {
            throw new IllegalArgumentException("El nombreCuenta es obligatorio");
        }
        if (solicitud.naturaleza() == null || solicitud.naturaleza().isBlank()) {
            throw new IllegalArgumentException("La naturaleza es obligatoria");
        }
        if (solicitud.grupoNivel1() == null || solicitud.grupoNivel1().isBlank()) {
            throw new IllegalArgumentException("El grupoNivel1 es obligatorio");
        }

        String codigoCuenta = solicitud.codigoCuenta().trim();

        if (planDeCuentasRepositorio.existePorCodigoCuenta(codigoCuenta)) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese codigoCuenta");
        }

        boolean esMovimiento = solicitud.esMovimiento() != null ? solicitud.esMovimiento() : true;
        boolean activo = solicitud.activo() != null ? solicitud.activo() : true;

        PlanDeCuentas nuevo = new PlanDeCuentas(
                UUID.randomUUID(),
                codigoCuenta,
                solicitud.nombreCuenta().trim(),
                solicitud.naturaleza().trim(),
                solicitud.grupoNivel1().trim(),
                solicitud.grupoNivel2(),
                solicitud.grupoNivel3(),
                esMovimiento,
                activo
        );

        PlanDeCuentas guardado = planDeCuentasRepositorio.guardar(nuevo);

        return new PlanDeCuentasResponse(
                guardado.planDeCuentasId(),
                guardado.codigoCuenta(),
                guardado.nombreCuenta(),
                guardado.naturaleza(),
                guardado.grupoNivel1(),
                guardado.grupoNivel2(),
                guardado.grupoNivel3(),
                guardado.esMovimiento(),
                guardado.activo()
        );
    }
}
