package com.smartfin.api.aplicacion.empresa.caso_uso;

import com.smartfin.api.dominio.empresa.modelo.Empresa;
import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ObtenerEmpresaCasoUso {

    private final EmpresaRepositorio repositorio;

    public ObtenerEmpresaCasoUso(EmpresaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Empresa ejecutar(UUID empresaId) {
        return repositorio.buscarPorId(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }
}
