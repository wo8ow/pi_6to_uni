package com.smartfin.api.adaptador.entrada.http.autenticacion.dto;

import java.util.List;
import java.util.UUID;

public class LoginResponse {

    private UUID usuarioId;
    private String nombreCompleto;
    private String correo;
    private String token;
    private List<String> roles;

    public LoginResponse() {}

    public LoginResponse(UUID usuarioId, String nombreCompleto, String correo, String token, List<String> roles) {
        this.usuarioId = usuarioId;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.token = token;
        this.roles = roles;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getToken() {
        return token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
