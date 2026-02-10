package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.kpi.modelo.Kpi;
import com.smartfin.api.dominio.kpi.puerto.KpiRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.entidad.KpiEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.jpa.repositorio.KpiRepositorioJpa;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class KpiRepositorioAdaptador implements KpiRepositorio {

    private final KpiRepositorioJpa kpiRepositorioJpa;

    public KpiRepositorioAdaptador(KpiRepositorioJpa kpiRepositorioJpa) {
        this.kpiRepositorioJpa = kpiRepositorioJpa;
    }

    @Override
    public Kpi guardar(Kpi kpi) {
        KpiEntidadJpa entidad = aEntidad(kpi);

        if (entidad.getKpiId() == null) {
            entidad.setKpiId(UUID.randomUUID());
        }
        if (entidad.getCreadoEn() == null) {
            entidad.setCreadoEn(OffsetDateTime.now());
        }
        if (entidad.getActualizadoEn() == null) {
            entidad.setActualizadoEn(OffsetDateTime.now());
        }

        KpiEntidadJpa guardada = kpiRepositorioJpa.save(entidad);
        return aDominio(guardada);
    }

    @Override
    public Optional<Kpi> buscarPorCodigo(String codigoKpi) {
        return kpiRepositorioJpa.findByCodigoKpiAndEliminadoEnIsNull(codigoKpi).map(this::aDominio);
    }

    @Override
    public boolean existePorCodigo(String codigoKpi) {
        return kpiRepositorioJpa.existsByCodigoKpiAndEliminadoEnIsNull(codigoKpi);
    }

    @Override
    public List<Kpi> listarActivos() {
        return kpiRepositorioJpa.findAllByEliminadoEnIsNullAndActivoTrue()
                .stream().map(this::aDominio).toList();
    }

    @Override
    public void eliminarPorCodigo(String codigoKpi) {
        KpiEntidadJpa entidad = kpiRepositorioJpa.findByCodigoKpiAndEliminadoEnIsNull(codigoKpi)
                .orElseThrow(() -> new IllegalArgumentException("No existe KPI con codigoKpi: " + codigoKpi));

        entidad.setActivo(false);
        entidad.setEliminadoEn(OffsetDateTime.now());
        entidad.setActualizadoEn(OffsetDateTime.now());
        kpiRepositorioJpa.save(entidad);
    }

    private KpiEntidadJpa aEntidad(Kpi kpi) {
        KpiEntidadJpa e = new KpiEntidadJpa();
        e.setKpiId(kpi.getKpiId());
        e.setCodigoKpi(kpi.getCodigoKpi());
        e.setNombreKpi(kpi.getNombreKpi());
        e.setDescripcion(kpi.getDescripcion());
        e.setUnidad(kpi.getUnidad() == null ? "USD" : kpi.getUnidad());
        e.setActivo(kpi.isActivo());
        e.setEliminadoEn(kpi.getEliminadoEn());
        e.setCreadoEn(kpi.getCreadoEn());
        e.setActualizadoEn(kpi.getActualizadoEn());
        return e;
    }

    private Kpi aDominio(KpiEntidadJpa e) {
        return new Kpi(
                e.getKpiId(),
                e.getCodigoKpi(),
                e.getNombreKpi(),
                e.getDescripcion(),
                e.getUnidad(),
                e.isActivo(),
                e.getEliminadoEn(),
                e.getCreadoEn(),
                e.getActualizadoEn()
        );
    }
}
