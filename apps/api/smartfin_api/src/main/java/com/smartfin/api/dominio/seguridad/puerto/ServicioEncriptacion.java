package com.smartfin.api.dominio.seguridad.puerto;

public interface ServicioEncriptacion {
    String encriptar(String clave);
    boolean verificar(String clave, String hash);
}
