package com.smartfin.api.infraestructura.persistencia.jpa;

import com.smartfin.api.dominio.balance.modelo.Balance;
import com.smartfin.api.dominio.balance.puerto.BalanceRepositorio;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BalanceRepositorioJpa implements BalanceRepositorio {

    private final BalanceJpaRepositorioSpring repo;

    public BalanceRepositorioJpa(BalanceJpaRepositorioSpring repo) {
        this.repo = repo;
    }

    @Override
    public Balance guardar(Balance balance) {
        BalanceEntidadJpa entidad = aEntidad(balance);
        BalanceEntidadJpa guardada = repo.save(entidad);
        return aDominio(guardada);
    }

    @Override
    public Optional<Balance> obtenerPorId(UUID balanceId) {
        return repo.findById(balanceId).map(this::aDominio);
    }

    @Override
    public Optional<Balance> buscarPorEmpresaPeriodoCuenta(UUID empresaId, UUID periodoId, String codigoCuenta) {
        return repo.findByEmpresaIdAndPeriodoIdAndCodigoCuenta(empresaId, periodoId, codigoCuenta)
                .map(this::aDominio);
    }

    @Override
    public List<Balance> listar(UUID empresaId, UUID periodoId) {

        if (empresaId != null && periodoId != null) {
            return repo.findByEmpresaIdAndPeriodoId(empresaId, periodoId).stream().map(this::aDominio).toList();
        }
        if (empresaId != null) {
            return repo.findByEmpresaId(empresaId).stream().map(this::aDominio).toList();
        }
        if (periodoId != null) {
            return repo.findByPeriodoId(periodoId).stream().map(this::aDominio).toList();
        }

        return repo.findAll().stream().map(this::aDominio).toList();
    }

    private BalanceEntidadJpa aEntidad(Balance b) {
        BalanceEntidadJpa e = new BalanceEntidadJpa();
        e.setBalanceId(b.getBalanceId());
        e.setEmpresaId(b.getEmpresaId());
        e.setPeriodoId(b.getPeriodoId());
        e.setCodigoCuenta(b.getCodigoCuenta());
        e.setSaldo(b.getSaldo());
        e.setMonedaCodigo(b.getMonedaCodigo());
        e.setFuente(b.getFuente());
        e.setCreadoEn(b.getCreadoEn());
        e.setActualizadoEn(b.getActualizadoEn());
        return e;
    }

    private Balance aDominio(BalanceEntidadJpa e) {
        return new Balance(
                e.getBalanceId(),
                e.getEmpresaId(),
                e.getPeriodoId(),
                e.getCodigoCuenta(),
                e.getSaldo(),
                e.getMonedaCodigo(),
                e.getFuente(),
                e.getCreadoEn(),
                e.getActualizadoEn()
        );
    }
}
