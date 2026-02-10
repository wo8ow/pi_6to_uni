package com.smartfin.api.aplicacion.periodo.caso_uso;

import com.smartfin.api.adaptador.entrada.http.periodo.dto.CrearPeriodoRequest;
import com.smartfin.api.adaptador.entrada.http.periodo.dto.PeriodoResponse;
import com.smartfin.api.dominio.periodo.modelo.Periodo;
import com.smartfin.api.dominio.periodo.modelo.TipoPeriodo;
import com.smartfin.api.dominio.periodo.puerto.PeriodoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearPeriodoCasoUso {

    private final PeriodoRepositorio periodoRepositorio;

    public CrearPeriodoCasoUso(PeriodoRepositorio periodoRepositorio) {
        this.periodoRepositorio = periodoRepositorio;
    }

    public PeriodoResponse ejecutar(CrearPeriodoRequest solicitud) {
        if (solicitud.periodoCodigo() == null || solicitud.periodoCodigo().isBlank()) {
            throw new IllegalArgumentException("El periodoCodigo es obligatorio");
        }
        if (solicitud.fechaInicio() == null || solicitud.fechaFin() == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias");
        }
        if (solicitud.fechaFin().isBefore(solicitud.fechaInicio())) {
            throw new IllegalArgumentException("La fechaFin no puede ser menor a la fechaInicio");
        }
        if (solicitud.tipoPeriodo() == null || solicitud.tipoPeriodo().isBlank()) {
            throw new IllegalArgumentException("El tipoPeriodo es obligatorio");
        }

        TipoPeriodo tipoPeriodo = TipoPeriodo.valueOf(solicitud.tipoPeriodo().trim().toUpperCase());

        boolean existe = periodoRepositorio.existePorCodigoYTipo(solicitud.periodoCodigo().trim(), tipoPeriodo.name());
        if (existe) {
            throw new IllegalArgumentException("Ya existe un periodo con ese codigo y tipo");
        }

        Periodo nuevo = new Periodo(
                UUID.randomUUID(),
                solicitud.periodoCodigo().trim(),
                solicitud.fechaInicio(),
                solicitud.fechaFin(),
                tipoPeriodo
        );

        Periodo guardado = periodoRepositorio.guardar(nuevo);

        return new PeriodoResponse(
                guardado.periodoId(),
                guardado.periodoCodigo(),
                guardado.fechaInicio(),
                guardado.fechaFin(),
                guardado.tipoPeriodo().name()
        );
    }
}
