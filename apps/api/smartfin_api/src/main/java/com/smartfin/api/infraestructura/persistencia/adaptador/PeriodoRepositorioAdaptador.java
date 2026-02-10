package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.periodo.modelo.Periodo;
import com.smartfin.api.dominio.periodo.modelo.TipoPeriodo;
import com.smartfin.api.dominio.periodo.puerto.PeriodoRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.PeriodoEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.jpa.PeriodoRepositorioJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PeriodoRepositorioAdaptador implements PeriodoRepositorio {

    private final PeriodoRepositorioJpa periodoRepositorioJpa;

    public PeriodoRepositorioAdaptador(PeriodoRepositorioJpa periodoRepositorioJpa) {
        this.periodoRepositorioJpa = periodoRepositorioJpa;
    }

    @Override
    public Periodo guardar(Periodo periodo) {
        PeriodoEntidadJpa entidad = new PeriodoEntidadJpa(
                periodo.periodoId(),
                periodo.periodoCodigo(),
                periodo.fechaInicio(),
                periodo.fechaFin(),
                periodo.tipoPeriodo().name()
        );

        PeriodoEntidadJpa guardada = periodoRepositorioJpa.save(entidad);

        return new Periodo(
                guardada.getPeriodoId(),
                guardada.getPeriodoCodigo(),
                guardada.getFechaInicio(),
                guardada.getFechaFin(),
                TipoPeriodo.valueOf(guardada.getTipoPeriodo())
        );
    }

    @Override
    public Optional<Periodo> buscarPorId(UUID periodoId) {
        return periodoRepositorioJpa.buscarActivoPorId(periodoId)
                .map(p -> new Periodo(
                        p.getPeriodoId(),
                        p.getPeriodoCodigo(),
                        p.getFechaInicio(),
                        p.getFechaFin(),
                        TipoPeriodo.valueOf(p.getTipoPeriodo())
                ));
    }

    @Override
    public List<Periodo> listar() {
        return periodoRepositorioJpa.listarActivos().stream()
                .map(p -> new Periodo(
                        p.getPeriodoId(),
                        p.getPeriodoCodigo(),
                        p.getFechaInicio(),
                        p.getFechaFin(),
                        TipoPeriodo.valueOf(p.getTipoPeriodo())
                ))
                .toList();
    }

    @Override
    public boolean existePorCodigoYTipo(String periodoCodigo, String tipoPeriodo) {
        return periodoRepositorioJpa.existePorCodigoYTipo(periodoCodigo, tipoPeriodo);
    }

    @Override
    @Transactional
    public boolean eliminar(UUID periodoId) {
        return periodoRepositorioJpa.softDelete(periodoId) > 0;
    }
}
