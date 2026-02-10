BEGIN;

-- ============================================================================
-- Archivo: infra/postgres/bi/001_ddl.sql
-- Base de datos: smartfin_inteligencia
-- Esquema: smartfin_inteligencia
-- Proposito: lectura rapida (tablero, comparacion, decision)
-- ============================================================================

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP SCHEMA IF EXISTS smartfin_inteligencia CASCADE;
CREATE SCHEMA smartfin_inteligencia;

COMMENT ON SCHEMA smartfin_inteligencia IS
'Esquema de inteligencia: tablas derivadas optimizadas para consulta rapida del tablero (una tupla por empresa y periodo en variaciones).';

-- ============================================================================
-- Utilidades
-- ============================================================================

CREATE OR REPLACE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
  NEW.actualizado_en := NOW();
RETURN NEW;
END;
$$;

COMMENT ON FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en() IS
'Actualiza el campo actualizado_en automaticamente en cada UPDATE.';

-- ============================================================================
-- Balance agregado por grupo (opcional para graficos rapidos)
-- ============================================================================

CREATE TABLE smartfin_inteligencia.balance_agregado_periodo (
                                                                balance_agregado_id  uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                empresa_origen_id    uuid NOT NULL,
                                                                periodo_codigo       varchar(16) NOT NULL,
                                                                grupo_nivel_1        varchar(80) NOT NULL,
                                                                grupo_nivel_2        varchar(120) NULL,
                                                                moneda_codigo        varchar(10) NOT NULL DEFAULT 'USD',
                                                                saldo                numeric(18,2) NOT NULL DEFAULT 0,
                                                                creado_en            timestamptz NOT NULL DEFAULT NOW(),
                                                                actualizado_en       timestamptz NOT NULL DEFAULT NOW()
);

CREATE INDEX ix_balance_agregado_empresa_periodo
    ON smartfin_inteligencia.balance_agregado_periodo (empresa_origen_id, periodo_codigo);

CREATE INDEX ix_balance_agregado_grupo
    ON smartfin_inteligencia.balance_agregado_periodo (grupo_nivel_1, grupo_nivel_2);

COMMENT ON TABLE smartfin_inteligencia.balance_agregado_periodo IS
'Hecho agregado: saldo por empresa y periodo resumido por grupos para graficos rapidos.';

CREATE TRIGGER trg_balance_agregado_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.balance_agregado_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();

-- ============================================================================
-- Variaciones horizontales de balance (una tupla por empresa y periodo)
-- ============================================================================

CREATE TABLE smartfin_inteligencia.balance_variacion_periodo (
                                                                 balance_variacion_id     uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                 empresa_origen_id        uuid NOT NULL,
                                                                 periodo_codigo           varchar(16) NOT NULL,
                                                                 moneda_codigo            varchar(10) NOT NULL DEFAULT 'USD',

                                                                 saldo_actual             numeric(18,2) NOT NULL DEFAULT 0,
                                                                 saldo_dia_anterior       numeric(18,2) NULL,
                                                                 saldo_mes_anterior       numeric(18,2) NULL,
                                                                 saldo_trimestre_anterior numeric(18,2) NULL,
                                                                 saldo_anio_anterior      numeric(18,2) NULL,
                                                                 saldo_acumulado_anio     numeric(18,2) NULL,

                                                                 variacion_diaria         numeric(18,2) NULL,
                                                                 variacion_mensual        numeric(18,2) NULL,
                                                                 variacion_trimestral     numeric(18,2) NULL,
                                                                 variacion_anual          numeric(18,2) NULL,
                                                                 variacion_acumulada_anio numeric(18,2) NULL,

                                                                 variacion_diaria_porcentaje         numeric(9,4) NULL,
                                                                 variacion_mensual_porcentaje        numeric(9,4) NULL,
                                                                 variacion_trimestral_porcentaje     numeric(9,4) NULL,
                                                                 variacion_anual_porcentaje          numeric(9,4) NULL,
                                                                 variacion_acumulada_anio_porcentaje numeric(9,4) NULL,

                                                                 creado_en                timestamptz NOT NULL DEFAULT NOW(),
                                                                 actualizado_en           timestamptz NOT NULL DEFAULT NOW(),

                                                                 CONSTRAINT ux_balance_variacion_periodo UNIQUE (empresa_origen_id, periodo_codigo)
);

COMMENT ON TABLE smartfin_inteligencia.balance_variacion_periodo IS
'Tabla principal para tablero: una fila por empresa y periodo con saldos y variaciones horizontales.';

CREATE TRIGGER trg_balance_variacion_periodo_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.balance_variacion_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();

-- ============================================================================
-- KPI: resultado por periodo y variacion horizontal por periodo
-- ============================================================================

CREATE TABLE smartfin_inteligencia.kpi_resultado_periodo (
                                                             kpi_resultado_id   uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                             empresa_origen_id   uuid NOT NULL,
                                                             periodo_codigo      varchar(16) NOT NULL,
                                                             codigo_kpi          varchar(60) NOT NULL,
                                                             nombre_kpi          varchar(200) NOT NULL,
                                                             unidad              varchar(40) NOT NULL DEFAULT 'USD',
                                                             valor               numeric(18,4) NOT NULL DEFAULT 0,
                                                             creado_en           timestamptz NOT NULL DEFAULT NOW(),
                                                             actualizado_en      timestamptz NOT NULL DEFAULT NOW(),
                                                             CONSTRAINT ux_kpi_resultado_periodo UNIQUE (empresa_origen_id, periodo_codigo, codigo_kpi)
);

CREATE INDEX ix_kpi_resultado_empresa_periodo
    ON smartfin_inteligencia.kpi_resultado_periodo (empresa_origen_id, periodo_codigo);

CREATE INDEX ix_kpi_resultado_kpi
    ON smartfin_inteligencia.kpi_resultado_periodo (codigo_kpi);

COMMENT ON TABLE smartfin_inteligencia.kpi_resultado_periodo IS
'Valores de kpi ya calculados para lectura rapida del tablero.';

CREATE TRIGGER trg_kpi_resultado_periodo_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.kpi_resultado_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();


CREATE TABLE smartfin_inteligencia.kpi_variacion_periodo (
                                                             kpi_variacion_id         uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                             empresa_origen_id        uuid NOT NULL,
                                                             periodo_codigo           varchar(16) NOT NULL,
                                                             codigo_kpi               varchar(60) NOT NULL,
                                                             unidad                   varchar(40) NOT NULL DEFAULT 'USD',

                                                             valor_actual             numeric(18,4) NOT NULL DEFAULT 0,
                                                             valor_dia_anterior       numeric(18,4) NULL,
                                                             valor_mes_anterior       numeric(18,4) NULL,
                                                             valor_trimestre_anterior numeric(18,4) NULL,
                                                             valor_anio_anterior      numeric(18,4) NULL,
                                                             valor_acumulado_anio     numeric(18,4) NULL,

                                                             variacion_diaria         numeric(18,4) NULL,
                                                             variacion_mensual        numeric(18,4) NULL,
                                                             variacion_trimestral     numeric(18,4) NULL,
                                                             variacion_anual          numeric(18,4) NULL,
                                                             variacion_acumulada_anio numeric(18,4) NULL,

                                                             variacion_diaria_porcentaje         numeric(9,4) NULL,
                                                             variacion_mensual_porcentaje        numeric(9,4) NULL,
                                                             variacion_trimestral_porcentaje     numeric(9,4) NULL,
                                                             variacion_anual_porcentaje          numeric(9,4) NULL,
                                                             variacion_acumulada_anio_porcentaje numeric(9,4) NULL,

                                                             creado_en                timestamptz NOT NULL DEFAULT NOW(),
                                                             actualizado_en           timestamptz NOT NULL DEFAULT NOW(),

                                                             CONSTRAINT ux_kpi_variacion_periodo UNIQUE (empresa_origen_id, periodo_codigo, codigo_kpi)
);

CREATE INDEX ix_kpi_variacion_empresa_periodo
    ON smartfin_inteligencia.kpi_variacion_periodo (empresa_origen_id, periodo_codigo);

CREATE INDEX ix_kpi_variacion_kpi
    ON smartfin_inteligencia.kpi_variacion_periodo (codigo_kpi);

COMMENT ON TABLE smartfin_inteligencia.kpi_variacion_periodo IS
'Variaciones horizontales de kpi para tablero: una fila por empresa, periodo y kpi.';

CREATE TRIGGER trg_kpi_variacion_periodo_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.kpi_variacion_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();

-- ============================================================================
-- Benchmark y riesgo (listos para consumo del tablero)
-- ============================================================================

CREATE TABLE smartfin_inteligencia.benchmark_sector_periodo (
                                                                benchmark_id       uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                sector             varchar(120) NOT NULL,
                                                                periodo_codigo     varchar(16) NOT NULL,
                                                                codigo_kpi         varchar(60) NOT NULL,
                                                                unidad             varchar(40) NOT NULL DEFAULT 'USD',
                                                                valor_sector       numeric(18,4) NOT NULL DEFAULT 0,
                                                                percentil          numeric(6,2) NULL,
                                                                fuente             varchar(200) NULL,
                                                                creado_en          timestamptz NOT NULL DEFAULT NOW(),
                                                                actualizado_en     timestamptz NOT NULL DEFAULT NOW(),
                                                                CONSTRAINT ux_benchmark_sector_periodo UNIQUE (sector, periodo_codigo, codigo_kpi)
);

CREATE INDEX ix_benchmark_sector_periodo
    ON smartfin_inteligencia.benchmark_sector_periodo (sector, periodo_codigo);

COMMENT ON TABLE smartfin_inteligencia.benchmark_sector_periodo IS
'Benchmark externo por sector y periodo para comparacion directa en tablero.';

CREATE TRIGGER trg_benchmark_sector_periodo_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.benchmark_sector_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();


CREATE TABLE smartfin_inteligencia.riesgo_resultado_periodo (
                                                                riesgo_id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                                                                empresa_origen_id   uuid NOT NULL,
                                                                periodo_codigo      varchar(16) NOT NULL,
                                                                nivel_riesgo        varchar(20) NOT NULL, -- bajo, medio, alto
                                                                puntaje_total       numeric(10,2) NULL,
                                                                regla_principal     varchar(120) NULL,
                                                                explicacion         text NULL,
                                                                creado_en           timestamptz NOT NULL DEFAULT NOW(),
                                                                actualizado_en      timestamptz NOT NULL DEFAULT NOW(),
                                                                CONSTRAINT ux_riesgo_resultado_periodo UNIQUE (empresa_origen_id, periodo_codigo)
);

COMMENT ON TABLE smartfin_inteligencia.riesgo_resultado_periodo IS
'Resultado final de riesgo listo para tablero (nivel y explicacion).';

CREATE TRIGGER trg_riesgo_resultado_periodo_actualizado_en
    BEFORE UPDATE ON smartfin_inteligencia.riesgo_resultado_periodo
    FOR EACH ROW
    EXECUTE FUNCTION smartfin_inteligencia.fn_actualizar_actualizado_en();

COMMIT;
