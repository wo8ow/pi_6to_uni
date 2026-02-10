package com.smartfin.api.adaptador.entrada.http.autenticacion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String clave;

    public LoginRequest() {}

    public LoginRequest(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
