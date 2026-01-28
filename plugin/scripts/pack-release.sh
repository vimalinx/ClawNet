#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PLUGIN_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"
OUT_DIR="${PLUGIN_DIR}/releases"

mkdir -p "${OUT_DIR}"

cd "${PLUGIN_DIR}"
archive="$(npm pack)"
mv "${archive}" "${OUT_DIR}/"

cat <<EOF
Package created:
  ${OUT_DIR}/${archive}

Customer install (offline):
  tar -xzf ${archive}
  ./package/scripts/install.sh
EOF
