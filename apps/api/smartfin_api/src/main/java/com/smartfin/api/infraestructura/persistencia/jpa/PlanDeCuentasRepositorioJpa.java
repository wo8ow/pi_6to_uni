package com.smartfin.api.infraestructura.persistencia.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanDeCuentasRepositorioJpa extends JpaRepository<PlanDeCuentasEntidadJpa, UUID> {
    Optional<PlanDeCuentasEntidadJpa> findByCodigoCuenta(String codigoCuenta);
    boolean existsByCodigoCuenta(String codigoCuenta);
}
