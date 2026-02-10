package com.smartfin.api.infraestructura.persistencia.repositorio_jpa;

import com.smartfin.api.infraestructura.persistencia.jpa.UsuarioRolEntidadJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRolRepositorioJpa extends JpaRepository<UsuarioRolEntidadJpa, UUID> {
    List<UsuarioRolEntidadJpa> findAllByUsuarioId(UUID usuarioId);
    Optional<UsuarioRolEntidadJpa> findByUsuarioIdAndRolId(UUID usuarioId, UUID rolId);
}
