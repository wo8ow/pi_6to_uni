package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.usuario.modelo.Rol;
import com.smartfin.api.dominio.usuario.puerto.RolRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.RolEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.RolRepositorioJpa;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RolRepositorioAdaptador implements RolRepositorio {

    private final RolRepositorioJpa rolRepositorioJpa;

    public RolRepositorioAdaptador(RolRepositorioJpa rolRepositorioJpa) {
        this.rolRepositorioJpa = rolRepositorioJpa;
    }

    @Override
    public Optional<Rol> buscarPorId(UUID rolId) {
        return rolRepositorioJpa.findById(rolId).map(this::aDominio);
    }

    @Override
    public List<Rol> buscarPorIds(List<UUID> rolIds) {
        if (rolIds == null || rolIds.isEmpty()) {
            return Collections.emptyList();
        }
        return rolRepositorioJpa.findAllByRolIdIn(rolIds).stream().map(this::aDominio).toList();
    }

    private Rol aDominio(RolEntidadJpa e) {
        return new Rol(
                e.getRolId(),
                e.getNombre(),
                e.getDescripcion(),
                e.getCreadoEn(),
                e.getActualizadoEn()
        );
    }
}
