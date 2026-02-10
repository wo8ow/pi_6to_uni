package com.smartfin.api.adaptador.entrada.http.autenticacion;

import com.smartfin.api.adaptador.entrada.http.autenticacion.dto.LoginRequest;
import com.smartfin.api.adaptador.entrada.http.autenticacion.dto.LoginResponse;
import com.smartfin.api.aplicacion.seguridad.caso_uso.IniciarSesionCasoUso;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/autenticacion")
public class AutenticacionControlador {

    private final IniciarSesionCasoUso iniciarSesionCasoUso;

    public AutenticacionControlador(IniciarSesionCasoUso iniciarSesionCasoUso) {
        this.iniciarSesionCasoUso = iniciarSesionCasoUso;
    }

    @PostMapping("/iniciar_sesion")
    public ResponseEntity<LoginResponse> iniciarSesion(@Valid @RequestBody LoginRequest request) {
        var respuesta = iniciarSesionCasoUso.ejecutar(request.getCorreo(), request.getClave());
        return ResponseEntity.ok(
                new LoginResponse(
                        respuesta.usuarioId(),
                        respuesta.nombreCompleto(),
                        respuesta.correo(),
                        respuesta.token(),
                        respuesta.roles()
                )
        );
    }
}
