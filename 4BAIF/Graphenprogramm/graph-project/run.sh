#!/bin/bash
# Kompiliert und startet den Graphenanalyzer
# Verwendung: ./run.sh [graph.csv] [--gui]

SRC="src/main/java"
OUT="out"
MAIN="graph.Main"

mkdir -p "$OUT"

echo "Kompiliere..."
javac -d "$OUT" $(find "$SRC" -name "*.java")

if [ $? -ne 0 ]; then
    echo "Kompilierungsfehler!"
    exit 1
fi

echo "Starte Graphenanalyzer..."
java -cp "$OUT" "$MAIN" "$@"
