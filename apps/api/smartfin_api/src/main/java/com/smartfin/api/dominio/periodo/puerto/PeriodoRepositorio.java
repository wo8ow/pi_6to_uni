package com.smartfin.api.dominio.periodo.puerto;

import com.smartfin.api.dominio.periodo.modelo.Periodo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeriodoRepositorio {

    Periodo guardar(Periodo periodo);

    Optional<Periodo> buscarPorId(UUID periodoId);

    List<Periodo> listar();

    boolean existePorCodigoYTipo(String periodoCodigo, String tipoPeriodo);

    boolean eliminar(UUID periodoId); // SOFT DELETE
}
