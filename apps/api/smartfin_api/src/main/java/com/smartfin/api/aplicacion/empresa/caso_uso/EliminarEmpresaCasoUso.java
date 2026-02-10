package com.smartfin.api.aplicacion.empresa.caso_uso;

import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarEmpresaCasoUso {

    private final EmpresaRepositorio repositorio;

    public EliminarEmpresaCasoUso(EmpresaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean ejecutar(UUID empresaId) {
        return repositorio.eliminar(empresaId);
    }
}
