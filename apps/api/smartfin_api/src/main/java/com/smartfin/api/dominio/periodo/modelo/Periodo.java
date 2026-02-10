package com.smartfin.api.dominio.periodo.modelo;

import java.time.LocalDate;
import java.util.UUID;

public record Periodo(
        UUID periodoId,
        String periodoCodigo,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        TipoPeriodo tipoPeriodo
) {}
