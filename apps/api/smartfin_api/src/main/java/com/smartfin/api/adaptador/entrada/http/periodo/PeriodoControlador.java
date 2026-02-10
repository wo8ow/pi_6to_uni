package com.smartfin.api.adaptador.entrada.http.periodo;

import com.smartfin.api.adaptador.entrada.http.periodo.dto.CrearPeriodoRequest;
import com.smartfin.api.adaptador.entrada.http.periodo.dto.PeriodoResponse;
import com.smartfin.api.aplicacion.periodo.caso_uso.CrearPeriodoCasoUso;
import com.smartfin.api.aplicacion.periodo.caso_uso.EliminarPeriodoCasoUso;
import com.smartfin.api.aplicacion.periodo.caso_uso.ListarPeriodosCasoUso;
import com.smartfin.api.aplicacion.periodo.caso_uso.ObtenerPeriodoCasoUso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/periodos")
public class PeriodoControlador {

    private final CrearPeriodoCasoUso crearPeriodoCasoUso;
    private final ListarPeriodosCasoUso listarPeriodosCasoUso;
    private final ObtenerPeriodoCasoUso obtenerPeriodoCasoUso;
    private final EliminarPeriodoCasoUso eliminarPeriodoCasoUso;

    public PeriodoControlador(
            CrearPeriodoCasoUso crearPeriodoCasoUso,
            ListarPeriodosCasoUso listarPeriodosCasoUso,
            ObtenerPeriodoCasoUso obtenerPeriodoCasoUso,
            EliminarPeriodoCasoUso eliminarPeriodoCasoUso
    ) {
        this.crearPeriodoCasoUso = crearPeriodoCasoUso;
        this.listarPeriodosCasoUso = listarPeriodosCasoUso;
        this.obtenerPeriodoCasoUso = obtenerPeriodoCasoUso;
        this.eliminarPeriodoCasoUso = eliminarPeriodoCasoUso;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public PeriodoResponse crear(@RequestBody CrearPeriodoRequest solicitud) {
        return crearPeriodoCasoUso.ejecutar(solicitud);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    public List<PeriodoResponse> listar() {
        return listarPeriodosCasoUso.ejecutar();
    }

    @GetMapping("/{periodoId}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    public PeriodoResponse obtener(@PathVariable UUID periodoId) {
        return obtenerPeriodoCasoUso.ejecutar(periodoId);
    }

    @DeleteMapping("/{periodoId}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminar(@PathVariable UUID periodoId) {
        boolean eliminado = eliminarPeriodoCasoUso.ejecutar(periodoId);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
