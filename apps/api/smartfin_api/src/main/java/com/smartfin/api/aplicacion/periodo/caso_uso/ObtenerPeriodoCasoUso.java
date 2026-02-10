package com.smartfin.api.aplicacion.periodo.caso_uso;

import com.smartfin.api.adaptador.entrada.http.periodo.dto.PeriodoResponse;
import com.smartfin.api.dominio.periodo.puerto.PeriodoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ObtenerPeriodoCasoUso {

    private final PeriodoRepositorio periodoRepositorio;

    public ObtenerPeriodoCasoUso(PeriodoRepositorio periodoRepositorio) {
        this.periodoRepositorio = periodoRepositorio;
    }

    public PeriodoResponse ejecutar(UUID periodoId) {
        var periodo = periodoRepositorio.buscarPorId(periodoId)
                .orElseThrow(() -> new IllegalArgumentException("Periodo no encontrado"));

        return new PeriodoResponse(
                periodo.periodoId(),
                periodo.periodoCodigo(),
                periodo.fechaInicio(),
                periodo.fechaFin(),
                periodo.tipoPeriodo().name()
        );
    }
}
