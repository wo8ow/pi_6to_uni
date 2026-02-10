#!/bin/bash
set -e

echo "Borrando toda la infraestructura SmartFin..."

docker compose --env-file .env \
  -f infra/docker/compose.base_de_datos.yml \
  -f infra/docker/compose.capas.yml down -v

docker volume prune -f
docker network prune -f

echo "Todo eliminado. Estado limpio."
