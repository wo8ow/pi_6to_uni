#!/bin/bash
set -e

echo "Deteniendo todos los servicios..."

docker compose --env-file .env \
  -f infra/docker/compose.base_de_datos.yml \
  -f infra/docker/compose.capas.yml down

echo "Todos los servicios detenidos."
