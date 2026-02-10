package com.smartfin.api.dominio.usuario.puerto;

import com.smartfin.api.dominio.usuario.modelo.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositorio {
    Optional<Usuario> buscarPorCorreo(String correo);
    Optional<Usuario> buscarPorCorreoYClave(String correo, String clave);
    Optional<Usuario> buscarPorId(UUID usuarioId);
}
