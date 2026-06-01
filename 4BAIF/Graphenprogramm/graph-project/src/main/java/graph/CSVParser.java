package graph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Liest eine Adjazenzmatrix aus einer CSV-Datei.
 *
 * Unterstützte Formate:
 * - Trennzeichen: Semikolon (;) oder Komma (,) – wird automatisch erkannt
 * - Zeilenende: \n oder \r\n (Windows)
 * - Optional: erste Zeile/Spalte als Knotenbezeichnungen
 * - Kantengewicht 0 = keine Kante; positive Werte = Kantengewicht
 */
public class CSVParser {

    /**
     * Liest die Adjazenzmatrix aus einer CSV-Datei ein.
     *
     * @param filePath  Pfad zur CSV-Datei
     * @param directed  true = gerichteter Graph, false = ungerichtet
     * @return Graph-Objekt mit Adjazenzmatrix und Knotenbezeichnungen
     * @throws IOException bei Lesefehler oder ungültigem Format
     */
    public static Graph parse(String filePath, boolean directed) throws IOException {
        List<String[]> rows = readRawRows(filePath);
        if (rows.isEmpty()) throw new IOException("CSV-Datei ist leer!");

        // Prüfen, ob erste Zeile Knotenbezeichnungen enthält
        boolean hasHeader = isHeaderRow(rows.get(0));

        String[] labels;
        double[][] matrix;
        int offset = hasHeader ? 1 : 0; // Zeilen-Offset wenn Header vorhanden
        int n = rows.size() - offset;   // Anzahl der Knoten

        if (n <= 0) throw new IOException("Keine Datensätze in der Datei gefunden!");

        // Knotenbezeichnungen bestimmen
        if (hasHeader) {
            String[] headerRow = rows.get(0);
            // Erste Spalte der Daten: Knotenname oder leer → überspringen
            int colOffset = isLabel(headerRow[0]) ? 1 : 0;
            labels = Arrays.copyOfRange(headerRow, colOffset, headerRow.length);
        } else {
            // Automatische Benennung: A, B, C, ...
            labels = new String[n];
            for (int i = 0; i < n; i++) {
                labels[i] = String.valueOf((char) ('A' + i));
            }
        }

        matrix = new double[n][n];

        // Zeilenweise einlesen
        for (int i = 0; i < n; i++) {
            String[] row = rows.get(i + offset);

            // Erste Spalte könnte Knotenbezeichnung sein → überspringen
            int colStart = hasHeader && isLabel(row[0]) ? 1 : 0;

            for (int j = 0; j < n; j++) {
                int colIndex = j + colStart;
                if (colIndex >= row.length) {
                    matrix[i][j] = 0;
                    continue;
                }
                String cell = row[colIndex].trim();
                try {
                    // Komma als Dezimaltrennzeichen ersetzen (z.B. "1,5" → "1.5")
                    matrix[i][j] = Double.parseDouble(cell.replace(',', '.'));
                } catch (NumberFormatException e) {
                    matrix[i][j] = 0; // Leer oder ungültig → keine Kante
                }
            }
        }

        return new Graph(matrix, labels, directed);
    }

    /**
     * Liest alle Zeilen der Datei und teilt sie nach dem erkannten Trennzeichen.
     */
    private static List<String[]> readRawRows(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) lines.add(line);
            }
        }

        if (lines.isEmpty()) return Collections.emptyList();

        // Trennzeichen automatisch erkennen: Semikolon oder Komma
        String delimiter = detectDelimiter(lines.get(0));

        List<String[]> rows = new ArrayList<>();
        for (String line : lines) {
            rows.add(line.split(delimiter, -1));
        }
        return rows;
    }

    /**
     * Erkennt das Trennzeichen: Semikolon (;) hat Vorrang vor Komma (,).
     */
    private static String detectDelimiter(String firstLine) {
        if (firstLine.contains(";")) return ";";
        return ",";
    }

    /**
     * Prüft, ob eine Zeile Knotenbezeichnungen enthält (d.h. nicht nur Zahlen).
     */
    private static boolean isHeaderRow(String[] row) {
        for (String cell : row) {
            cell = cell.trim();
            if (cell.isEmpty()) continue;
            try {
                Double.parseDouble(cell.replace(',', '.'));
            } catch (NumberFormatException e) {
                return true; // Text gefunden → Header-Zeile
            }
        }
        return false;
    }

    /**
     * Prüft, ob ein Zellwert eine Knotenbezeichnung (kein Zahl) ist.
     */
    private static boolean isLabel(String cell) {
        cell = cell.trim();
        if (cell.isEmpty()) return false;
        try {
            Double.parseDouble(cell.replace(',', '.'));
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
