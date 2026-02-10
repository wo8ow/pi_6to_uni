package com.smartfin.api.aplicacion.kpi.caso_uso;

import com.smartfin.api.dominio.kpi.modelo.Kpi;
import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ObtenerKpiCasoUso {
    private final KpiRepositorio kpiRepositorio;

    public ObtenerKpiCasoUso(KpiRepositorio kpiRepositorio) {
        this.kpiRepositorio = kpiRepositorio;
    }

    public Kpi ejecutar(String codigoKpi) {
        return kpiRepositorio.buscarPorCodigo(codigoKpi)
                .orElseThrow(() -> new IllegalArgumentException("No existe KPI con codigoKpi: " + codigoKpi));
    }
}
