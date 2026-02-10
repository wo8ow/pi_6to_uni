package com.smartfin.api.aplicacion.plan_de_cuentas.caso_uso;

import com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto.PlanDeCuentasResponse;
import com.smartfin.api.dominio.plan_de_cuentas.puerto.PlanDeCuentasRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPlanDeCuentasCasoUso {

    private final PlanDeCuentasRepositorio planDeCuentasRepositorio;

    public ListarPlanDeCuentasCasoUso(PlanDeCuentasRepositorio planDeCuentasRepositorio) {
        this.planDeCuentasRepositorio = planDeCuentasRepositorio;
    }

    public List<PlanDeCuentasResponse> ejecutar() {
        return planDeCuentasRepositorio.listar().stream()
                .map(c -> new PlanDeCuentasResponse(
                        c.planDeCuentasId(),
                        c.codigoCuenta(),
                        c.nombreCuenta(),
                        c.naturaleza(),
                        c.grupoNivel1(),
                        c.grupoNivel2(),
                        c.grupoNivel3(),
                        c.esMovimiento(),
                        c.activo()
                ))
                .toList();
    }
}
