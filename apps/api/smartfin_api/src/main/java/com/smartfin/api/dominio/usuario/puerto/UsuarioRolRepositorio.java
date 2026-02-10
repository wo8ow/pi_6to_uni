package com.smartfin.api.dominio.usuario.puerto;

import com.smartfin.api.dominio.usuario.modelo.UsuarioRol;

import java.util.List;
import java.util.UUID;

public interface UsuarioRolRepositorio {
    List<UsuarioRol> listarPorUsuarioId(UUID usuarioId);
}
