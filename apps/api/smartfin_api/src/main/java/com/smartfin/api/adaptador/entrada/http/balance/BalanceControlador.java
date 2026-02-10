package com.smartfin.api.adaptador.entrada.http.balance;

import com.smartfin.api.adaptador.entrada.http.balance.dto.RegistrarBalanceRequest;
import com.smartfin.api.aplicacion.balance.caso_uso.ConsultarBalanceCasoUso;
import com.smartfin.api.aplicacion.balance.caso_uso.RegistrarBalanceCasoUso;
import com.smartfin.api.aplicacion.balance.comando.RegistrarBalanceComando;
import com.smartfin.api.dominio.balance.modelo.Balance;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/balances")
public class BalanceControlador {

    private final RegistrarBalanceCasoUso registrarBalanceCasoUso;
    private final ConsultarBalanceCasoUso consultarBalanceCasoUso;

    public BalanceControlador(RegistrarBalanceCasoUso registrarBalanceCasoUso,
                              ConsultarBalanceCasoUso consultarBalanceCasoUso) {
        this.registrarBalanceCasoUso = registrarBalanceCasoUso;
        this.consultarBalanceCasoUso = consultarBalanceCasoUso;
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO')")
    @PostMapping
    public ResponseEntity<Balance> registrar(@Valid @RequestBody RegistrarBalanceRequest request) {

        RegistrarBalanceComando comando = new RegistrarBalanceComando(
                request.getEmpresaId(),
                request.getPeriodoId(),
                request.getCodigoCuenta(),
                request.getSaldo(),
                request.getMonedaCodigo(),
                request.getFuente()
        );

        Balance creado = registrarBalanceCasoUso.ejecutar(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    @GetMapping
    public ResponseEntity<List<Balance>> listar(
            @RequestParam(required = false) UUID empresaId,
            @RequestParam(required = false) UUID periodoId
    ) {
        return ResponseEntity.ok(consultarBalanceCasoUso.listar(empresaId, periodoId));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','FINANCIERO','REPORTERIA')")
    @GetMapping("/{balanceId}")
    public ResponseEntity<Balance> obtener(@PathVariable UUID balanceId) {
        return ResponseEntity.ok(consultarBalanceCasoUso.obtenerPorId(balanceId));
    }
}
