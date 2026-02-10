package com.smartfin.api.dominio.kpi.puerto;

import com.smartfin.api.dominio.kpi.modelo.Kpi;

import java.util.List;
import java.util.Optional;

public interface KpiRepositorio {
    Kpi guardar(Kpi kpi);

    Optional<Kpi> buscarPorCodigo(String codigoKpi);

    boolean existePorCodigo(String codigoKpi);

    List<Kpi> listarActivos();

    void eliminarPorCodigo(String codigoKpi);
}
