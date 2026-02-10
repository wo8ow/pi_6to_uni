package com.smartfin.api.aplicacion.empresa.caso_uso;

import com.smartfin.api.dominio.empresa.modelo.Empresa;
import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarEmpresasCasoUso {

    private final EmpresaRepositorio repositorio;

    public ListarEmpresasCasoUso(EmpresaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Empresa> ejecutar() {
        return repositorio.listar();
    }
}
