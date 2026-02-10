package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.usuario.modelo.UsuarioRol;
import com.smartfin.api.dominio.usuario.puerto.UsuarioRolRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.UsuarioRolEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.UsuarioRolRepositorioJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioRolRepositorioAdaptador implements UsuarioRolRepositorio {

    private final UsuarioRolRepositorioJpa usuarioRolRepositorioJpa;

    public UsuarioRolRepositorioAdaptador(UsuarioRolRepositorioJpa usuarioRolRepositorioJpa) {
        this.usuarioRolRepositorioJpa = usuarioRolRepositorioJpa;
    }

    @Override
    public List<UsuarioRol> listarPorUsuarioId(UUID usuarioId) {
        return usuarioRolRepositorioJpa.findAllByUsuarioId(usuarioId).stream()
                .map(this::aDominio)
                .toList();
    }

    private UsuarioRol aDominio(UsuarioRolEntidadJpa e) {
        return new UsuarioRol(
                e.getUsuarioRolId(),
                e.getUsuarioId(),
                e.getRolId(),
                e.getCreadoEn(),
                e.getActualizadoEn()
        );
    }
}
