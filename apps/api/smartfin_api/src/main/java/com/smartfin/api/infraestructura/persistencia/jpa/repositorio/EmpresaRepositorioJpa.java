package com.smartfin.api.infraestructura.persistencia.jpa.repositorio;

import com.smartfin.api.infraestructura.persistencia.jpa.entidad.EmpresaEntidadJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepositorioJpa extends JpaRepository<EmpresaEntidadJpa, UUID> {

    Optional<EmpresaEntidadJpa> findByEmpresaIdAndActivoTrueAndEliminadoEnIsNull(UUID empresaId);

    List<EmpresaEntidadJpa> findAllByActivoTrueAndEliminadoEnIsNull();

    @Modifying
    @Query("""
        update EmpresaEntidadJpa e
           set e.activo = false,
               e.eliminadoEn = CURRENT_TIMESTAMP
         where e.empresaId = :empresaId
           and e.activo = true
           and e.eliminadoEn is null
    """)
    int softDelete(@Param("empresaId") UUID empresaId);
}
