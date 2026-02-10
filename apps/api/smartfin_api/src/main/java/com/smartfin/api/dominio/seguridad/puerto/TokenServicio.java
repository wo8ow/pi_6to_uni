package com.smartfin.api.dominio.seguridad.puerto;

import java.util.List;
import java.util.UUID;

public interface TokenServicio {

    String generarToken(UUID usuarioId, String correo, List<String> roles);

}
