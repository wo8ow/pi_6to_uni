package com.smartfin.api.infraestructura.seguridad;

import com.smartfin.api.dominio.seguridad.puerto.ServicioEncriptacion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptServicioEncriptacion implements ServicioEncriptacion {

    private final PasswordEncoder passwordEncoder;

    public BcryptServicioEncriptacion(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encriptar(String clave) {
        return passwordEncoder.encode(clave);
    }

    @Override
    public boolean verificar(String clave, String hash) {
        return passwordEncoder.matches(clave, hash);
    }
}
