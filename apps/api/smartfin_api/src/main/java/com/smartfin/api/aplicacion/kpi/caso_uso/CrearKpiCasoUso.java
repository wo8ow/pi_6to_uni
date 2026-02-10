package com.smartfin.api.aplicacion.kpi.caso_uso;

import com.smartfin.api.dominio.kpi.modelo.Kpi;
import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import org.springframework.stereotype.Service;

@Service
public class CrearKpiCasoUso {
    private final KpiRepositorio kpiRepositorio;

    public CrearKpiCasoUso(KpiRepositorio kpiRepositorio) {
        this.kpiRepositorio = kpiRepositorio;
    }

    public Kpi ejecutar(Kpi kpi) {
        if (kpi.getCodigoKpi() == null || kpi.getCodigoKpi().isBlank()) {
            throw new IllegalArgumentException("codigoKpi es obligatorio");
        }
        if (kpi.getNombreKpi() == null || kpi.getNombreKpi().isBlank()) {
            throw new IllegalArgumentException("nombreKpi es obligatorio");
        }
        if (kpi.getUnidad() == null || kpi.getUnidad().isBlank()) {
            kpi.setUnidad("USD");
        }
        if (kpiRepositorio.existePorCodigo(kpi.getCodigoKpi())) {
            throw new IllegalArgumentException("Ya existe KPI con codigoKpi: " + kpi.getCodigoKpi());
        }
        kpi.setActivo(true);
        return kpiRepositorio.guardar(kpi);
    }
}
