package com.smartfin.api.adaptador.entrada.http.empresa.mapper;

import com.smartfin.api.adaptador.entrada.http.empresa.dto.EmpresaResponse;
import com.smartfin.api.dominio.empresa.modelo.Empresa;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class EmpresaMapper {

    private EmpresaMapper() {
        // utility
    }

    public static EmpresaResponse aRespuesta(Empresa empresa) {
        if (empresa == null) return null;

        return new EmpresaResponse(
                empresa.getEmpresaId(),
                empresa.getIdentificacion(),
                empresa.getNombre(),
                empresa.getSector(),
                empresa.getMonedaCodigo(),
                empresa.isActivo()
        );
    }

    public static List<EmpresaResponse> aRespuestas(List<Empresa> empresas) {
        if (empresas == null) return List.of();
        return empresas.stream().map(EmpresaMapper::aRespuesta).collect(Collectors.toList());
    }

    public static UUID id(Empresa empresa) {
        return empresa == null ? null : empresa.getEmpresaId();
    }
}
