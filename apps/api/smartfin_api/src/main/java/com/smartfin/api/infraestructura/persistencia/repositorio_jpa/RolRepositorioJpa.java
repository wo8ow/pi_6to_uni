package com.smartfin.api.infraestructura.persistencia.repositorio_jpa;

import com.smartfin.api.infraestructura.persistencia.jpa.RolEntidadJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolRepositorioJpa extends JpaRepository<RolEntidadJpa, UUID> {
    List<RolEntidadJpa> findAllByRolIdIn(List<UUID> rolIds);
    Optional<RolEntidadJpa> findByNombre(String nombre);
}
