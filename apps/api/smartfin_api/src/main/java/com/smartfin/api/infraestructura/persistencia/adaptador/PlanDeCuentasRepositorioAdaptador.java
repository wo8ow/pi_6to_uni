package com.smartfin.api.infraestructura.persistencia.adaptador;

import com.smartfin.api.dominio.plan_de_cuentas.modelo.PlanDeCuentas;
import com.smartfin.api.dominio.plan_de_cuentas.puerto.PlanDeCuentasRepositorio;
import com.smartfin.api.infraestructura.persistencia.jpa.PlanDeCuentasEntidadJpa;
import com.smartfin.api.infraestructura.persistencia.jpa.PlanDeCuentasRepositorioJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PlanDeCuentasRepositorioAdaptador implements PlanDeCuentasRepositorio {

    private final PlanDeCuentasRepositorioJpa repositorioJpa;

    public PlanDeCuentasRepositorioAdaptador(PlanDeCuentasRepositorioJpa repositorioJpa) {
        this.repositorioJpa = repositorioJpa;
    }

    @Override
    public PlanDeCuentas guardar(PlanDeCuentas planDeCuentas) {
        PlanDeCuentasEntidadJpa entidad = new PlanDeCuentasEntidadJpa(
                planDeCuentas.planDeCuentasId(),
                planDeCuentas.codigoCuenta(),
                planDeCuentas.nombreCuenta(),
                planDeCuentas.naturaleza(),
                planDeCuentas.grupoNivel1(),
                planDeCuentas.grupoNivel2(),
                planDeCuentas.grupoNivel3(),
                planDeCuentas.esMovimiento(),
                planDeCuentas.activo()
        );

        PlanDeCuentasEntidadJpa guardada = repositorioJpa.save(entidad);

        return new PlanDeCuentas(
                guardada.getPlanDeCuentasId(),
                guardada.getCodigoCuenta(),
                guardada.getNombreCuenta(),
                guardada.getNaturaleza(),
                guardada.getGrupoNivel1(),
                guardada.getGrupoNivel2(),
                guardada.getGrupoNivel3(),
                guardada.isEsMovimiento(),
                guardada.isActivo()
        );
    }

    @Override
    public Optional<PlanDeCuentas> buscarPorId(UUID planDeCuentasId) {
        return repositorioJpa.findById(planDeCuentasId)
                .map(c -> new PlanDeCuentas(
                        c.getPlanDeCuentasId(),
                        c.getCodigoCuenta(),
                        c.getNombreCuenta(),
                        c.getNaturaleza(),
                        c.getGrupoNivel1(),
                        c.getGrupoNivel2(),
                        c.getGrupoNivel3(),
                        c.isEsMovimiento(),
                        c.isActivo()
                ));
    }

    @Override
    public Optional<PlanDeCuentas> buscarPorCodigoCuenta(String codigoCuenta) {
        return repositorioJpa.findByCodigoCuenta(codigoCuenta)
                .map(c -> new PlanDeCuentas(
                        c.getPlanDeCuentasId(),
                        c.getCodigoCuenta(),
                        c.getNombreCuenta(),
                        c.getNaturaleza(),
                        c.getGrupoNivel1(),
                        c.getGrupoNivel2(),
                        c.getGrupoNivel3(),
                        c.isEsMovimiento(),
                        c.isActivo()
                ));
    }

    @Override
    public List<PlanDeCuentas> listar() {
        return repositorioJpa.findAll().stream()
                .map(c -> new PlanDeCuentas(
                        c.getPlanDeCuentasId(),
                        c.getCodigoCuenta(),
                        c.getNombreCuenta(),
                        c.getNaturaleza(),
                        c.getGrupoNivel1(),
                        c.getGrupoNivel2(),
                        c.getGrupoNivel3(),
                        c.isEsMovimiento(),
                        c.isActivo()
                ))
                .toList();
    }

    @Override
    public boolean existePorCodigoCuenta(String codigoCuenta) {
        return repositorioJpa.existsByCodigoCuenta(codigoCuenta);
    }
}
