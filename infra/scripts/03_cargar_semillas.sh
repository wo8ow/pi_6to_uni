#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../.."

set -a
. ./.env
set +a

echo "Cargando semillas iniciales..."

docker compose --env-file .env \
  -f infra/docker/compose.base_de_datos.yml \
  exec -T postgres_operacional \
  psql -v ON_ERROR_STOP=1 \
  -U "$SF_OPER_USER" \
  -d "$SF_OPER_DB" \
  < bootstrap/postgres/operacional/002_seed_datos_iniciales.sql

echo "Semillas cargadas correctamente."
