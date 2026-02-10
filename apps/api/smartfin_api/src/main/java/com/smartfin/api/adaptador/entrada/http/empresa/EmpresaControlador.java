package com.smartfin.api.adaptador.entrada.http.empresa;

import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaActualizarRequest;
import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaCrearRequest;
import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaResponse;
import com.smartfin.api.aplicacion.empresa.caso_uso.ActualizarEmpresaCasoUso;
import com.smartfin.api.aplicacion.empresa.caso_uso.CrearEmpresaCasoUso;
import com.smartfin.api.aplicacion.empresa.caso_uso.ListarEmpresasCasoUso;
import com.smartfin.api.aplicacion.empresa.caso_uso.ObtenerEmpresaCasoUso;
import com.smartfin.api.dominio.empresa.modelo.Empresa;
import org.springframework.web.bind.annotation.*;
import com.smartfin.api.aplicacion.empresa.caso_uso.EliminarEmpresaCasoUso;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaControlador {

    private final CrearEmpresaCasoUso crear;
    private final ListarEmpresasCasoUso listar;
    private final ObtenerEmpresaCasoUso obtener;
    private final ActualizarEmpresaCasoUso actualizar;
    private final EliminarEmpresaCasoUso eliminar;

    public EmpresaControlador(
            CrearEmpresaCasoUso crear,
            ListarEmpresasCasoUso listar,
            ObtenerEmpresaCasoUso obtener,
            ActualizarEmpresaCasoUso actualizar,
            EliminarEmpresaCasoUso eliminar
    ) {
        this.crear = crear;
        this.listar = listar;
        this.obtener = obtener;
        this.actualizar = actualizar;
        this.eliminar = eliminar;
    }

    @PostMapping
    public EmpresaResponse crear(@RequestBody EmpresaCrearRequest solicitud) {
        Empresa e = crear.ejecutar(solicitud);
        return mapear(e);
    }

    @GetMapping
    public List<EmpresaResponse> listar() {
        return listar.ejecutar().stream()
                .map(this::mapear)
                .collect(Collectors.toList());
    }

    @GetMapping("/{empresaId}")
    public EmpresaResponse obtener(@PathVariable UUID empresaId) {
        return mapear(obtener.ejecutar(empresaId));
    }

    @PutMapping("/{empresaId}")
    public EmpresaResponse actualizar(
            @PathVariable UUID empresaId,
            @RequestBody EmpresaActualizarRequest solicitud) {
        return mapear(actualizar.ejecutar(empresaId, solicitud));
    }

    @DeleteMapping("/{empresaId}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID empresaId) {
        boolean eliminado = eliminar.ejecutar(empresaId);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    private EmpresaResponse mapear(Empresa e) {
        return new EmpresaResponse(
                e.getEmpresaId(),
                e.getIdentificacion(),
                e.getNombre(),
                e.getSector(),
                e.getMonedaCodigo(),
                e.isActivo()
        );
    }
}
