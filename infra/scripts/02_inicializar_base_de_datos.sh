#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../.."

test -f .env || { echo "ERROR: falta .env en la raiz del proyecto"; exit 1; }
set -a
. ./.env
set +a

test -f bootstrap/postgres/operacional/001_ddl.sql || { echo "ERROR: falta bootstrap/postgres/operacional/001_ddl.sql"; exit 1; }
test -f bootstrap/postgres/inteligencia/001_ddl.sql || { echo "ERROR: falta bootstrap/postgres/inteligencia/001_ddl.sql"; exit 1; }

echo "Verificando contenedores de base de datos..."
if ! docker ps --format '{{.Names}}' | grep -qx "smartfin-postgres-oper"; then
  echo "Base no levantada. Ejecutando 01_levantar_base_de_datos.sh..."
  sh infra/scripts/01_levantar_base_de_datos.sh
fi

echo "Esperando postgres operacional..."
for i in {1..30}; do
  if docker exec smartfin-postgres-oper pg_isready -U "${SF_OPER_USER}" -d "${SF_OPER_DB}" >/dev/null 2>&1; then
    break
  fi
  sleep 2
done

echo "Esperando postgres inteligencia..."
for i in {1..30}; do
  if docker exec smartfin-postgres-bi pg_isready -U "${SF_BI_USER}" -d "${SF_BI_DB}" >/dev/null 2>&1; then
    break
  fi
  sleep 2
done

echo "Aplicando DDL operacional en ${SF_OPER_DB}..."
docker compose --env-file .env -f infra/docker/compose.base_de_datos.yml exec -T postgres_operacional \
  psql -v ON_ERROR_STOP=1 -U "${SF_OPER_USER}" -d "${SF_OPER_DB}" \
  < bootstrap/postgres/operacional/001_ddl.sql

echo "Aplicando DDL inteligencia en ${SF_BI_DB}..."
docker compose --env-file .env -f infra/docker/compose.base_de_datos.yml exec -T postgres_inteligencia \
  psql -v ON_ERROR_STOP=1 -U "${SF_BI_USER}" -d "${SF_BI_DB}" \
  < bootstrap/postgres/inteligencia/001_ddl.sql

echo "Inicializacion completada: operacional + inteligencia."
