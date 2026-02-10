package com.smartfin.api.aplicacion.empresa.caso_uso;

import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaActualizarRequest;
import com.smartfin.api.dominio.empresa.modelo.Empresa;
import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActualizarEmpresaCasoUso {

    private final EmpresaRepositorio repositorio;

    public ActualizarEmpresaCasoUso(EmpresaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Empresa ejecutar(UUID empresaId, EmpresaActualizarRequest solicitud) {
        Empresa empresa = repositorio.buscarPorId(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Empresa actualizada = new Empresa(
                empresa.getEmpresaId(),
                empresa.getIdentificacion(),
                solicitud.getNombre(),
                solicitud.getSector(),
                solicitud.getMonedaCodigo(),
                solicitud.isActivo()
        );

        return repositorio.guardar(actualizada);
    }
}
