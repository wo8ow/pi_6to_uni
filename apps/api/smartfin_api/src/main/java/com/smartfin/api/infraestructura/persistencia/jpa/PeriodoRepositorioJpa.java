package com.smartfin.api.infraestructura.persistencia.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PeriodoRepositorioJpa extends JpaRepository<PeriodoEntidadJpa, UUID> {

    @Query("""
           select case when count(p) > 0 then true else false end
           from PeriodoEntidadJpa p
           where p.periodoCodigo = :codigo
             and p.tipoPeriodo = :tipo
             and p.eliminadoEn is null
           """)
    boolean existePorCodigoYTipo(@Param("codigo") String codigo, @Param("tipo") String tipo);

    @Query("""
           select p from PeriodoEntidadJpa p
           where p.eliminadoEn is null and p.activo = true
           """)
    List<PeriodoEntidadJpa> listarActivos();

    @Query("""
           select p from PeriodoEntidadJpa p
           where p.periodoId = :id and p.eliminadoEn is null and p.activo = true
           """)
    Optional<PeriodoEntidadJpa> buscarActivoPorId(@Param("id") UUID id);

    @Modifying
    @Query("""
           update PeriodoEntidadJpa p
           set p.activo = false,
               p.eliminadoEn = CURRENT_TIMESTAMP
           where p.periodoId = :id
             and p.eliminadoEn is null
           """)
    int softDelete(@Param("id") UUID id);
}
