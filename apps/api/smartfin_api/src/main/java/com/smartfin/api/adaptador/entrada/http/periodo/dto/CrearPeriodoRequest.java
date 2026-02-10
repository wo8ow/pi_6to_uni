package com.smartfin.api.adaptador.entrada.http.periodo.dto;

import java.time.LocalDate;

public record CrearPeriodoRequest(
        String periodoCodigo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String tipoPeriodo
) {}
