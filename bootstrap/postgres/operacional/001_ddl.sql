BEGIN;

-- ============================================================================
-- Base: smartfin_operacional
-- Esquema: smartfin
-- ============================================================================

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP SCHEMA IF EXISTS smartfin CASCADE;
CREATE SCHEMA smartfin;

-- ============================================================================
-- Utilidad común
-- ============================================================================

CREATE OR REPLACE FUNCTION smartfin.fn_actualizar_actualizado_en()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
  NEW.actualizado_en := NOW();
RETURN NEW;
END;
$$;

-- ============================================================================
-- Seguridad
-- ============================================================================

CREATE TABLE smartfin.usuario (
                                  usuario_id       uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                  correo           varchar(254) NOT NULL,
                                  nombre_completo  varchar(200) NOT NULL,
                                  clave_hash       varchar(200) NOT NULL,
                                  activo           boolean NOT NULL DEFAULT true,
                                  eliminado_en     timestamptz NULL,
                                  creado_en        timestamptz NOT NULL DEFAULT NOW(),
                                  actualizado_en   timestamptz NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX ux_usuario_correo ON smartfin.usuario (correo);

CREATE TRIGGER trg_usuario_actualizado_en
    BEFORE UPDATE ON smartfin.usuario
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();


CREATE TABLE smartfin.rol (
                              rol_id           uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                              nombre           varchar(80) NOT NULL,
                              descripcion      varchar(300),
                              activo           boolean NOT NULL DEFAULT true,
                              eliminado_en     timestamptz NULL,
                              creado_en        timestamptz NOT NULL DEFAULT NOW(),
                              actualizado_en   timestamptz NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX ux_rol_nombre ON smartfin.rol (nombre);

CREATE TRIGGER trg_rol_actualizado_en
    BEFORE UPDATE ON smartfin.rol
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();


CREATE TABLE smartfin.usuario_rol (
                                      usuario_rol_id   uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                      usuario_id       uuid NOT NULL,
                                      rol_id           uuid NOT NULL,
                                      activo           boolean NOT NULL DEFAULT true,
                                      eliminado_en     timestamptz NULL,
                                      creado_en        timestamptz NOT NULL DEFAULT NOW(),
                                      actualizado_en   timestamptz NOT NULL DEFAULT NOW(),
                                      CONSTRAINT fk_ur_usuario FOREIGN KEY (usuario_id) REFERENCES smartfin.usuario(usuario_id),
                                      CONSTRAINT fk_ur_rol FOREIGN KEY (rol_id) REFERENCES smartfin.rol(rol_id),
                                      CONSTRAINT ux_usuario_rol UNIQUE (usuario_id, rol_id)
);

CREATE TRIGGER trg_usuario_rol_actualizado_en
    BEFORE UPDATE ON smartfin.usuario_rol
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

-- ============================================================================
-- Empresa y Periodo
-- ============================================================================

CREATE TABLE smartfin.empresa (
                                  empresa_id        uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                  identificacion    varchar(32) NOT NULL,
                                  nombre            varchar(200) NOT NULL,
                                  sector            varchar(120),
                                  moneda_codigo     varchar(10) NOT NULL DEFAULT 'USD',
                                  activo            boolean NOT NULL DEFAULT true,
                                  eliminado_en      timestamptz NULL,
                                  creado_en         timestamptz NOT NULL DEFAULT NOW(),
                                  actualizado_en    timestamptz NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX ux_empresa_identificacion ON smartfin.empresa (identificacion);

CREATE TRIGGER trg_empresa_actualizado_en
    BEFORE UPDATE ON smartfin.empresa
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();


CREATE TABLE smartfin.periodo (
                                  periodo_id        uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                  periodo_codigo    varchar(16) NOT NULL,
                                  fecha_inicio      date NOT NULL,
                                  fecha_fin         date NOT NULL,
                                  tipo_periodo      varchar(20) NOT NULL,
                                  activo            boolean NOT NULL DEFAULT true,
                                  eliminado_en      timestamptz NULL,
                                  creado_en         timestamptz NOT NULL DEFAULT NOW(),
                                  actualizado_en    timestamptz NOT NULL DEFAULT NOW(),
                                  CONSTRAINT ux_periodo UNIQUE (periodo_codigo, tipo_periodo)
);

CREATE TRIGGER trg_periodo_actualizado_en
    BEFORE UPDATE ON smartfin.periodo
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

-- ============================================================================
-- Plan de Cuentas
-- ============================================================================

CREATE TABLE smartfin.plan_de_cuentas (
                                          plan_de_cuentas_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                          codigo_cuenta      varchar(40) NOT NULL,
                                          nombre_cuenta      varchar(200) NOT NULL,
                                          naturaleza         varchar(20) NOT NULL,
                                          grupo_nivel_1      varchar(80) NOT NULL,
                                          grupo_nivel_2      varchar(120),
                                          grupo_nivel_3      varchar(120),
                                          es_movimiento      boolean NOT NULL DEFAULT true,
                                          activo             boolean NOT NULL DEFAULT true,
                                          eliminado_en       timestamptz NULL,
                                          creado_en          timestamptz NOT NULL DEFAULT NOW(),
                                          actualizado_en     timestamptz NOT NULL DEFAULT NOW(),
                                          CONSTRAINT ux_plan_codigo UNIQUE (codigo_cuenta)
);

CREATE TRIGGER trg_plan_actualizado_en
    BEFORE UPDATE ON smartfin.plan_de_cuentas
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

-- ============================================================================
-- Balance (verdad del sistema – NO soft delete)
-- ============================================================================

CREATE TABLE smartfin.balance (
                                  balance_id        uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                  empresa_id        uuid NOT NULL,
                                  periodo_id        uuid NOT NULL,
                                  codigo_cuenta     varchar(40) NOT NULL,
                                  saldo             numeric(18,2) NOT NULL DEFAULT 0,
                                  moneda_codigo     varchar(10) NOT NULL DEFAULT 'USD',
                                  fuente            varchar(120),
                                  creado_en         timestamptz NOT NULL DEFAULT NOW(),
                                  actualizado_en    timestamptz NOT NULL DEFAULT NOW(),
                                  CONSTRAINT fk_balance_empresa FOREIGN KEY (empresa_id) REFERENCES smartfin.empresa(empresa_id),
                                  CONSTRAINT fk_balance_periodo FOREIGN KEY (periodo_id) REFERENCES smartfin.periodo(periodo_id),
                                  CONSTRAINT fk_balance_cuenta FOREIGN KEY (codigo_cuenta) REFERENCES smartfin.plan_de_cuentas(codigo_cuenta),
                                  CONSTRAINT ux_balance UNIQUE (empresa_id, periodo_id, codigo_cuenta)
);

CREATE TRIGGER trg_balance_actualizado_en
    BEFORE UPDATE ON smartfin.balance
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

-- ============================================================================
-- KPI y Relaciones
-- ============================================================================

CREATE TABLE smartfin.kpi (
                              kpi_id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                              codigo_kpi      varchar(60) NOT NULL,
                              nombre_kpi      varchar(200) NOT NULL,
                              descripcion     varchar(500),
                              unidad          varchar(40) NOT NULL DEFAULT 'USD',
                              activo          boolean NOT NULL DEFAULT true,
                              eliminado_en    timestamptz NULL,
                              creado_en       timestamptz NOT NULL DEFAULT NOW(),
                              actualizado_en  timestamptz NOT NULL DEFAULT NOW(),
                              CONSTRAINT ux_kpi_codigo UNIQUE (codigo_kpi)
);

CREATE TRIGGER trg_kpi_actualizado_en
    BEFORE UPDATE ON smartfin.kpi
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();


CREATE TABLE smartfin.kpi_cuenta (
                                     kpi_cuenta_id   uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                     kpi_id          uuid NOT NULL,
                                     codigo_cuenta   varchar(40) NOT NULL,
                                     peso            numeric(12,4) NOT NULL DEFAULT 1,
                                     signo           integer NOT NULL DEFAULT 1,
                                     activo          boolean NOT NULL DEFAULT true,
                                     eliminado_en    timestamptz NULL,
                                     creado_en       timestamptz NOT NULL DEFAULT NOW(),
                                     actualizado_en  timestamptz NOT NULL DEFAULT NOW(),
                                     CONSTRAINT fk_kpi_cuenta_kpi FOREIGN KEY (kpi_id) REFERENCES smartfin.kpi(kpi_id),
                                     CONSTRAINT fk_kpi_cuenta_cuenta FOREIGN KEY (codigo_cuenta) REFERENCES smartfin.plan_de_cuentas(codigo_cuenta),
                                     CONSTRAINT ux_kpi_cuenta UNIQUE (kpi_id, codigo_cuenta)
);

CREATE TRIGGER trg_kpi_cuenta_actualizado_en
    BEFORE UPDATE ON smartfin.kpi_cuenta
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

-- ============================================================================
-- Resultados y Variaciones (auditables – NO delete)
-- ============================================================================

CREATE TABLE smartfin.kpi_resultado (
                                        kpi_resultado_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                        empresa_id       uuid NOT NULL,
                                        periodo_id       uuid NOT NULL,
                                        codigo_kpi       varchar(60) NOT NULL,
                                        valor            numeric(18,4) NOT NULL,
                                        unidad           varchar(40),
                                        creado_en        timestamptz NOT NULL DEFAULT NOW(),
                                        actualizado_en   timestamptz NOT NULL DEFAULT NOW()
);

CREATE TRIGGER trg_kpi_resultado_actualizado_en
    BEFORE UPDATE ON smartfin.kpi_resultado
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();


CREATE TABLE smartfin.kpi_variacion (
                                        kpi_variacion_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                        empresa_id       uuid NOT NULL,
                                        periodo_id       uuid NOT NULL,
                                        codigo_kpi       varchar(60) NOT NULL,
                                        valor_actual     numeric(18,4),
                                        valor_referencia numeric(18,4),
                                        variacion        numeric(18,4),
                                        variacion_porcentaje numeric(9,4),
                                        tipo_variacion   varchar(20),
                                        creado_en        timestamptz NOT NULL DEFAULT NOW(),
                                        actualizado_en   timestamptz NOT NULL DEFAULT NOW()
);

CREATE TRIGGER trg_kpi_variacion_actualizado_en
    BEFORE UPDATE ON smartfin.kpi_variacion
    FOR EACH ROW EXECUTE FUNCTION smartfin.fn_actualizar_actualizado_en();

COMMIT;
