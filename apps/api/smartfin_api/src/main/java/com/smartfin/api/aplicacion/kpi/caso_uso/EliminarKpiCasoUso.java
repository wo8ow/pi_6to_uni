package com.smartfin.api.aplicacion.kpi.caso_uso;

import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import org.springframework.stereotype.Service;

@Service
public class EliminarKpiCasoUso {
    private final KpiRepositorio kpiRepositorio;

    public EliminarKpiCasoUso(KpiRepositorio kpiRepositorio) {
        this.kpiRepositorio = kpiRepositorio;
    }

    public void ejecutar(String codigoKpi) {
        kpiRepositorio.eliminarPorCodigo(codigoKpi);
    }
}
