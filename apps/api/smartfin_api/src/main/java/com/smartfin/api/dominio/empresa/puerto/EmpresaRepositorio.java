package com.smartfin.api.dominio.empresa.puerto;

import com.smartfin.api.dominio.empresa.modelo.Empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepositorio {

    Empresa guardar(Empresa empresa);

    Optional<Empresa> buscarPorId(UUID empresaId);

    List<Empresa> listar();

    boolean eliminar(UUID empresaId);
}
