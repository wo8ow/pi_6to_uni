package com.smartfin.api.infraestructura.persistencia.jpa;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "plan_de_cuentas", schema = "smartfin")
public class PlanDeCuentasEntidadJpa {

    @Id
    @Column(name = "plan_de_cuentas_id", nullable = false)
    private UUID planDeCuentasId;

    @Column(name = "codigo_cuenta", nullable = false, length = 40)
    private String codigoCuenta;

    @Column(name = "nombre_cuenta", nullable = false, length = 200)
    private String nombreCuenta;

    @Column(name = "naturaleza", nullable = false, length = 20)
    private String naturaleza;

    @Column(name = "grupo_nivel_1", nullable = false, length = 80)
    private String grupoNivel1;

    @Column(name = "grupo_nivel_2", length = 120)
    private String grupoNivel2;

    @Column(name = "grupo_nivel_3", length = 120)
    private String grupoNivel3;

    @Column(name = "es_movimiento", nullable = false)
    private boolean esMovimiento;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn;

    protected PlanDeCuentasEntidadJpa() {}

    public PlanDeCuentasEntidadJpa(
            UUID planDeCuentasId,
            String codigoCuenta,
            String nombreCuenta,
            String naturaleza,
            String grupoNivel1,
            String grupoNivel2,
            String grupoNivel3,
            boolean esMovimiento,
            boolean activo
    ) {
        this.planDeCuentasId = planDeCuentasId;
        this.codigoCuenta = codigoCuenta;
        this.nombreCuenta = nombreCuenta;
        this.naturaleza = naturaleza;
        this.grupoNivel1 = grupoNivel1;
        this.grupoNivel2 = grupoNivel2;
        this.grupoNivel3 = grupoNivel3;
        this.esMovimiento = esMovimiento;
        this.activo = activo;
    }

    public UUID getPlanDeCuentasId() { return planDeCuentasId; }
    public String getCodigoCuenta() { return codigoCuenta; }
    public String getNombreCuenta() { return nombreCuenta; }
    public String getNaturaleza() { return naturaleza; }
    public String getGrupoNivel1() { return grupoNivel1; }
    public String getGrupoNivel2() { return grupoNivel2; }
    public String getGrupoNivel3() { return grupoNivel3; }
    public boolean isEsMovimiento() { return esMovimiento; }
    public boolean isActivo() { return activo; }
}
