package com.smartfin.api.aplicacion.kpi.caso_uso;

import com.smartfin.api.dominio.kpi.modelo.Kpi;
import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarKpisCasoUso {
    private final KpiRepositorio kpiRepositorio;

    public ListarKpisCasoUso(KpiRepositorio kpiRepositorio) {
        this.kpiRepositorio = kpiRepositorio;
    }

    public List<Kpi> ejecutar() {
        return kpiRepositorio.listarActivos();
    }
}
