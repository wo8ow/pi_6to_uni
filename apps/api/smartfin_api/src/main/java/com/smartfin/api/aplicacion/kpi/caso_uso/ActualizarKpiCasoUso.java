package com.smartfin.api.aplicacion.kpi.caso_uso;

import com.smartfin.api.dominio.kpi.modelo.Kpi;
import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ActualizarKpiCasoUso {
    private final KpiRepositorio kpiRepositorio;

    public ActualizarKpiCasoUso(KpiRepositorio kpiRepositorio) {
        this.kpiRepositorio = kpiRepositorio;
    }

    public Kpi ejecutar(String codigoKpi, Kpi cambios) {
        Kpi actual = kpiRepositorio.buscarPorCodigo(codigoKpi)
                .orElseThrow(() -> new IllegalArgumentException("No existe KPI con codigoKpi: " + codigoKpi));

        if (cambios.getNombreKpi() != null && !cambios.getNombreKpi().isBlank()) {
            actual.setNombreKpi(cambios.getNombreKpi());
        }
        if (cambios.getDescripcion() != null) {
            actual.setDescripcion(cambios.getDescripcion());
        }
        if (cambios.getUnidad() != null && !cambios.getUnidad().isBlank()) {
            actual.setUnidad(cambios.getUnidad());
        }
        return kpiRepositorio.guardar(actual);
    }
}
