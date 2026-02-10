package com.smartfin.api.aplicacion.periodo.caso_uso;

import com.smartfin.api.adaptador.entrada.http.periodo.dto.PeriodoResponse;
import com.smartfin.api.dominio.periodo.puerto.PeriodoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPeriodosCasoUso {

    private final PeriodoRepositorio periodoRepositorio;

    public ListarPeriodosCasoUso(PeriodoRepositorio periodoRepositorio) {
        this.periodoRepositorio = periodoRepositorio;
    }

    public List<PeriodoResponse> ejecutar() {
        return periodoRepositorio.listar().stream()
                .map(p -> new PeriodoResponse(
                        p.periodoId(),
                        p.periodoCodigo(),
                        p.fechaInicio(),
                        p.fechaFin(),
                        p.tipoPeriodo().name()
                ))
                .toList();
    }
}
