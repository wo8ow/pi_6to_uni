package com.smartfin.api.dominio.usuario.puerto;

import com.smartfin.api.dominio.usuario.modelo.Rol;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolRepositorio {
    Optional<Rol> buscarPorId(UUID rolId);
    List<Rol> buscarPorIds(List<UUID> rolIds);
}
