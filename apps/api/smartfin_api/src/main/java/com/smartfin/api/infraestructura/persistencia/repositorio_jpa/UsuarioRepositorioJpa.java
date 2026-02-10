package com.smartfin.api.infraestructura.persistencia.repositorio_jpa;

import com.smartfin.api.infraestructura.persistencia.jpa.UsuarioEntidadJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositorioJpa extends JpaRepository<UsuarioEntidadJpa, UUID> {

    Optional<UsuarioEntidadJpa> findByCorreo(String correo);

    @Query(value = """
        select *
        from smartfin.usuario
        where correo = :correo
          and clave_hash = crypt(:clave, clave_hash)
        """, nativeQuery = true)
    Optional<UsuarioEntidadJpa> buscarPorCorreoYClave(@Param("correo") String correo, @Param("clave") String clave);
}
