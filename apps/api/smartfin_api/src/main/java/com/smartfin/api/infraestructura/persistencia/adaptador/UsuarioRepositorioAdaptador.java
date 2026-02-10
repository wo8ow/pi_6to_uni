package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.usuario.modelo.Usuario;
import com.smartfin.api.dominio.usuario.puerto.UsuarioRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.UsuarioEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.repositorio_jpa.UsuarioRepositorioJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UsuarioRepositorioAdaptador implements UsuarioRepositorio {

    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    public UsuarioRepositorioAdaptador(UsuarioRepositorioJpa usuarioRepositorioJpa) {
        this.usuarioRepositorioJpa = usuarioRepositorioJpa;
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepositorioJpa.findByCorreo(correo).map(this::aDominio);
    }

    @Override
    public Optional<Usuario> buscarPorCorreoYClave(String correo, String clave) {
        return usuarioRepositorioJpa.buscarPorCorreoYClave(correo, clave).map(this::aDominio);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID usuarioId) {
        return usuarioRepositorioJpa.findById(usuarioId).map(this::aDominio);
    }

    private Usuario aDominio(UsuarioEntidadJpa e) {
        return new Usuario(
                e.getUsuarioId(),
                e.getCorreo(),
                e.getNombreCompleto(),
                e.getClaveHash(),
                Boolean.TRUE.equals(e.getActivo()),
                e.getCreadoEn(),
                e.getActualizadoEn()
        );
    }
}
