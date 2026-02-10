package com.smartfin.api.aplicacion.empresa.caso_uso;

import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaCrearRequest;
import com.smartfin.api.dominio.empresa.modelo.Empresa;
import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrearEmpresaCasoUso {

    private final EmpresaRepositorio repositorio;

    public CrearEmpresaCasoUso(EmpresaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Empresa ejecutar(EmpresaCrearRequest solicitud) {
        Empresa empresa = new Empresa(
                UUID.randomUUID(),
                solicitud.getIdentificacion(),
                solicitud.getNombre(),
                solicitud.getSector(),
                solicitud.getMonedaCodigo(),
                true
        );
        return repositorio.guardar(empresa);
    }
}
