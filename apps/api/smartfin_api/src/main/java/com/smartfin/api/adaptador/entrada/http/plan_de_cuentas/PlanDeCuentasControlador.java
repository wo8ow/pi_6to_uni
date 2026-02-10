package com.smartfin.api.adaptador.entrada.http.plan_de_cuentas;

import com.smartfin.api.aplicacion.plan_de_cuentas.caso_uso.CrearPlanDeCuentasCasoUso;
import com.smartfin.api.aplicacion.plan_de_cuentas.caso_uso.ListarPlanDeCuentasCasoUso;
import com.smartfin.api.aplicacion.plan_de_cuentas.caso_uso.ObtenerPlanDeCuentasCasoUso;
import com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto.CrearPlanDeCuentasRequest;
import com.smartfin.api.adaptador.entrada.http.plan_de_cuentas.dto.PlanDeCuentasResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plan_de_cuentas")
public class PlanDeCuentasControlador {

    private final CrearPlanDeCuentasCasoUso crearPlanDeCuentasCasoUso;
    private final ListarPlanDeCuentasCasoUso listarPlanDeCuentasCasoUso;
    private final ObtenerPlanDeCuentasCasoUso obtenerPlanDeCuentasCasoUso;

    public PlanDeCuentasControlador(
            CrearPlanDeCuentasCasoUso crearPlanDeCuentasCasoUso,
            ListarPlanDeCuentasCasoUso listarPlanDeCuentasCasoUso,
            ObtenerPlanDeCuentasCasoUso obtenerPlanDeCuentasCasoUso
    ) {
        this.crearPlanDeCuentasCasoUso = crearPlanDeCuentasCasoUso;
        this.listarPlanDeCuentasCasoUso = listarPlanDeCuentasCasoUso;
        this.obtenerPlanDeCuentasCasoUso = obtenerPlanDeCuentasCasoUso;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public PlanDeCuentasResponse crear(@RequestBody CrearPlanDeCuentasRequest solicitud) {
        return crearPlanDeCuentasCasoUso.ejecutar(solicitud);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    public List<PlanDeCuentasResponse> listar() {
        return listarPlanDeCuentasCasoUso.ejecutar();
    }

    @GetMapping("/{planDeCuentasId}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    public PlanDeCuentasResponse obtener(@PathVariable UUID planDeCuentasId) {
        return obtenerPlanDeCuentasCasoUso.ejecutar(planDeCuentasId);
    }
}
