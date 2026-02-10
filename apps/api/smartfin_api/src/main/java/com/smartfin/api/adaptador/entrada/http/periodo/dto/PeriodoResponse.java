package com.smartfin.api.adaptador.entrada.http.periodo.dto;

import java.time.LocalDate;
import java.util.UUID;

public record PeriodoResponse(
        UUID periodoId,
        String periodoCodigo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String tipoPeriodo
) {}
