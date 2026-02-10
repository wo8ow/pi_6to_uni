package com.smartfin.api.infraestructura.persistencia.jpa.repositorio;

import com.smartfin.api.infraestructura.persistencia.jpa.entidad.KpiEntidadJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KpiRepositorioJpa extends JpaRepository<KpiEntidadJpa, UUID> {

    Optional<KpiEntidadJpa> findByCodigoKpiAndEliminadoEnIsNull(String codigoKpi);

    boolean existsByCodigoKpiAndEliminadoEnIsNull(String codigoKpi);

    List<KpiEntidadJpa> findAllByEliminadoEnIsNullAndActivoTrue();
}
