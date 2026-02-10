#!/usr/bin/env bash
set -euo pipefail

echo "Levantando SmartFin..."

docker compose \
  --env-file .env \
  -f infra/docker/compose.base_de_datos.yml \
  up -d

echo "SmartFin levantado."
