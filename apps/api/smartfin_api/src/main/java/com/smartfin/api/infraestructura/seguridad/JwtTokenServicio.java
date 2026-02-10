package com.smartfin.api.infraestructura.seguridad;

import com.smartfin.api.dominio.seguridad.puerto.TokenServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JwtTokenServicio implements TokenServicio {

    private final JwtServicio jwtServicio;

    public JwtTokenServicio(JwtServicio jwtServicio) {
        this.jwtServicio = jwtServicio;
    }

    @Override
    public String generarToken(UUID usuarioId, String correo, List<String> roles) {
        return jwtServicio.generarToken(usuarioId, correo, roles);
    }
}
