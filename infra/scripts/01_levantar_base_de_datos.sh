#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../.."

# cargar .env
test -f .env || { echo "ERROR: falta .env en la raiz del proyecto"; exit 1; }
set -a
. ./.env
set +a

echo "Levantando bases de datos SmartFin..."

docker compose --env-file .env -f infra/docker/compose.base_de_datos.yml up -d

echo "Bases de datos levantadas."
echo "Postgres operacional: localhost:${SF_OPER_PORT}"
echo "Postgres inteligencia: localhost:${SF_BI_PORT}"
echo "pgAdmin: http://localhost:${SF_PGADMIN_PORT}"
