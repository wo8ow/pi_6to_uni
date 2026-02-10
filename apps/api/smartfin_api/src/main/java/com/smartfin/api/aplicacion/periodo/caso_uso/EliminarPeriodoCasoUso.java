package com.smartfin.api.aplicacion.periodo.caso_uso;

import com.smartfin.api.dominio.periodo.puerto.PeriodoRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarPeriodoCasoUso {

    private final PeriodoRepositorio repositorio;

    public EliminarPeriodoCasoUso(PeriodoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean ejecutar(UUID periodoId) {
        return repositorio.eliminar(periodoId);
    }
}
