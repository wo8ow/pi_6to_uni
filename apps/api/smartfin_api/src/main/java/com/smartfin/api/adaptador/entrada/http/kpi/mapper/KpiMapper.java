package com.smartfin.api.adaptador.entrada.http.kpi.mapper;

import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiActualizarRequest;
import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiCrearRequest;
import com.smartfin.api.adaptador.entrada.http.kpi.dto.KpiResponse;
import com.smartfin.api.dominio.kpi.modelo.Kpi;
import org.springframework.stereotype.Component;

@Component
public class KpiMapper {

    public Kpi aDominio(KpiCrearRequest req) {
        Kpi kpi = new Kpi();
        kpi.setCodigoKpi(req.getCodigoKpi());
        kpi.setNombreKpi(req.getNombreKpi());
        kpi.setDescripcion(req.getDescripcion());
        kpi.setUnidad(req.getUnidad());
        kpi.setActivo(true);
        return kpi;
    }

    public Kpi aCambios(KpiActualizarRequest req) {
        Kpi kpi = new Kpi();
        kpi.setNombreKpi(req.getNombreKpi());
        kpi.setDescripcion(req.getDescripcion());
        kpi.setUnidad(req.getUnidad());
        return kpi;
    }

    public KpiResponse aResponse(Kpi kpi) {
        KpiResponse res = new KpiResponse();
        res.setKpiId(kpi.getKpiId());
        res.setCodigoKpi(kpi.getCodigoKpi());
        res.setNombreKpi(kpi.getNombreKpi());
        res.setDescripcion(kpi.getDescripcion());
        res.setUnidad(kpi.getUnidad());
        res.setActivo(kpi.isActivo());
        res.setCreadoEn(kpi.getCreadoEn());
        res.setActualizadoEn(kpi.getActualizadoEn());
        return res;
    }
}
