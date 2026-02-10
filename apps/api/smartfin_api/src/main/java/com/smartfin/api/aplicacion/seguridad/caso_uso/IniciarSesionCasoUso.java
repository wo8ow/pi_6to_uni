package com.smartfin.api.aplicacion.seguridad.caso_uso;

import com.smartfin.api.dominio.seguridad.puerto.TokenServicio;
import com.smartfin.api.dominio.usuario.modelo.Rol;
import com.smartfin.api.dominio.usuario.modelo.Usuario;
import com.smartfin.api.dominio.usuario.modelo.UsuarioRol;
import com.smartfin.api.dominio.usuario.puerto.RolRepositorio;
import com.smartfin.api.dominio.usuario.puerto.UsuarioRepositorio;
import com.smartfin.api.dominio.usuario.puerto.UsuarioRolRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IniciarSesionCasoUso {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;
    private final RolRepositorio rolRepositorio;
    private final TokenServicio tokenServicio;

    public IniciarSesionCasoUso(
            UsuarioRepositorio usuarioRepositorio,
            UsuarioRolRepositorio usuarioRolRepositorio,
            RolRepositorio rolRepositorio,
            TokenServicio tokenServicio
    ) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.tokenServicio = tokenServicio;
    }

    public Resultado ejecutar(String correo, String clave) {

        Usuario usuario = usuarioRepositorio.buscarPorCorreoYClave(correo, clave)
                .orElseThrow(() -> new IllegalArgumentException("Usuario o clave incorrectos"));

        if (!usuario.isActivo()) {
            throw new IllegalArgumentException("Usuario inactivo");
        }

        List<UsuarioRol> relaciones = usuarioRolRepositorio.listarPorUsuarioId(usuario.getUsuarioId());
        List<UUID> rolIds = relaciones.stream().map(UsuarioRol::getRolId).toList();
        List<Rol> roles = rolRepositorio.buscarPorIds(rolIds);

        List<String> nombresRoles = roles.stream().map(Rol::getNombre).sorted().toList();

        String tokenJwt = tokenServicio.generarToken(usuario.getUsuarioId(), usuario.getCorreo(), nombresRoles);

        return new Resultado(
                usuario.getUsuarioId(),
                usuario.getNombreCompleto(),
                usuario.getCorreo(),
                tokenJwt,
                nombresRoles
        );
    }

    public record Resultado(
            UUID usuarioId,
            String nombreCompleto,
            String correo,
            String token,
            List<String> roles
    ) {}
}
