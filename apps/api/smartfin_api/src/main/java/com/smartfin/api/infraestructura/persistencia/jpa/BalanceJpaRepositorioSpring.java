package com.smartfin.api.infraestructura.persistencia.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceJpaRepositorioSpring extends JpaRepository<BalanceEntidadJpa, UUID> {

    Optional<BalanceEntidadJpa> findByEmpresaIdAndPeriodoIdAndCodigoCuenta(UUID empresaId, UUID periodoId, String codigoCuenta);

    List<BalanceEntidadJpa> findByEmpresaIdAndPeriodoId(UUID empresaId, UUID periodoId);

    List<BalanceEntidadJpa> findByEmpresaId(UUID empresaId);

    List<BalanceEntidadJpa> findByPeriodoId(UUID periodoId);
}
