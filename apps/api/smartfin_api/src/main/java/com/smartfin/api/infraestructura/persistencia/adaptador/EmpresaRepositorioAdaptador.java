package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.empresa.modelo.Empresa;
import com.smartfin.api.dominio.empresa.puerto.EmpresaRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.entidad.EmpresaEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.jpa.repositorio.EmpresaRepositorioJpa;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EmpresaRepositorioAdaptador implements EmpresaRepositorio {

    private final EmpresaRepositorioJpa jpa;

    public EmpresaRepositorioAdaptador(EmpresaRepositorioJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        EmpresaEntidadJpa e = new EmpresaEntidadJpa();
        e.setEmpresaId(empresa.getEmpresaId());
        e.setIdentificacion(empresa.getIdentificacion());
        e.setNombre(empresa.getNombre());
        e.setSector(empresa.getSector());
        e.setMonedaCodigo(empresa.getMonedaCodigo());
        e.setActivo(empresa.isActivo());
        // eliminadoEn no se setea aquí normalmente; lo controla soft delete
        jpa.save(e);
        return empresa;
    }

    @Override
    public Optional<Empresa> buscarPorId(UUID empresaId) {
        return jpa.findByEmpresaIdAndActivoTrueAndEliminadoEnIsNull(empresaId)
                .map(e -> new Empresa(
                        e.getEmpresaId(),
                        e.getIdentificacion(),
                        e.getNombre(),
                        e.getSector(),
                        e.getMonedaCodigo(),
                        e.isActivo()
                ));
    }

    @Override
    public List<Empresa> listar() {
        return jpa.findAllByActivoTrueAndEliminadoEnIsNull().stream()
                .map(e -> new Empresa(
                        e.getEmpresaId(),
                        e.getIdentificacion(),
                        e.getNombre(),
                        e.getSector(),
                        e.getMonedaCodigo(),
                        e.isActivo()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean eliminar(UUID empresaId) {
        return jpa.softDelete(empresaId) > 0;
    }
}
