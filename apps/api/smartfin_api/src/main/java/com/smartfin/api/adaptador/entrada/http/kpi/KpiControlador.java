package com.smartfin.api.adaptador.entrada.http.kpi;

import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiActualizarRequest;
import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiCrearRequest;
import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiResponse;
import com.smartfin.api.adaptador.entrada.http.kpi.mapper.KpiMapper;
import com.smartfin.api.aplicacion.kpi.caso_uso.*;
import com.smartfin.api.dominio.kpi.modelo.Kpi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kpis")
public class KpiControlador {

    private final CrearKpiCasoUso crearKpiCasoUso;
    private final ObtenerKpiCasoUso obtenerKpiCasoUso;
    private final ListarKpisCasoUso listarKpisCasoUso;
    private final ActualizarKpiCasoUso actualizarKpiCasoUso;
    private final EliminarKpiCasoUso eliminarKpiCasoUso;
    private final KpiMapper kpiMapper;

    public KpiControlador(CrearKpiCasoUso crearKpiCasoUso,
                          ObtenerKpiCasoUso obtenerKpiCasoUso,
                          ListarKpisCasoUso listarKpisCasoUso,
                          ActualizarKpiCasoUso actualizarKpiCasoUso,
                          EliminarKpiCasoUso eliminarKpiCasoUso,
                          KpiMapper kpiMapper) {
        this.crearKpiCasoUso = crearKpiCasoUso;
        this.obtenerKpiCasoUso = obtenerKpiCasoUso;
        this.listarKpisCasoUso = listarKpisCasoUso;
        this.actualizarKpiCasoUso = actualizarKpiCasoUso;
        this.eliminarKpiCasoUso = eliminarKpiCasoUso;
        this.kpiMapper = kpiMapper;
    }

    @PostMapping
    public ResponseEntity<KpiResponse> crear(@RequestBody KpiCrearRequest request) {
        Kpi creado = crearKpiCasoUso.ejecutar(kpiMapper.aDominio(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(kpiMapper.aResponse(creado));
    }

    @GetMapping
    public ResponseEntity<List<KpiResponse>> listar() {
        List<KpiResponse> res = listarKpisCasoUso.ejecutar().stream().map(kpiMapper::aResponse).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{codigoKpi}")
    public ResponseEntity<KpiResponse> obtener(@PathVariable String codigoKpi) {
        Kpi kpi = obtenerKpiCasoUso.ejecutar(codigoKpi);
        return ResponseEntity.ok(kpiMapper.aResponse(kpi));
    }

    @PutMapping("/{codigoKpi}")
    public ResponseEntity<KpiResponse> actualizar(@PathVariable String codigoKpi,
                                                  @RequestBody KpiActualizarRequest request) {
        Kpi actualizado = actualizarKpiCasoUso.ejecutar(codigoKpi, kpiMapper.aCambios(request));
        return ResponseEntity.ok(kpiMapper.aResponse(actualizado));
    }

    @DeleteMapping("/{codigoKpi}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigoKpi) {
        eliminarKpiCasoUso.ejecutar(codigoKpi);
        return ResponseEntity.noContent().build();
    }
}
