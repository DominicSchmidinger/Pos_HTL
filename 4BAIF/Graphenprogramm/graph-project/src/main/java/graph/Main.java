package graph;

import java.util.*;

/**
 * Kommandozeilen-Einstiegspunkt.
 *
 * Verwendung:
 *   java graph.Main graph.csv              → vollständige Analyse
 *   java graph.Main graph.csv A            → Dijkstra von Knoten A
 *   java graph.Main graph.csv A B          → Dijkstra-Pfad von A nach B
 *   java graph.Main --gui graph.csv        → GUI starten (mit optionaler Datei)
 *   java graph.Main --directed graph.csv  → gerichteten Graph laden
 */
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        // Flags auswerten
        boolean useGui = false;
        boolean directed = false;
        List<String> positional = new ArrayList<>();

        for (String arg : args) {
            switch (arg.toLowerCase()) {
                case "--gui":    useGui = true;    break;
                case "--directed": directed = true; break;
                default:        positional.add(arg); break;
            }
        }

        // GUI-Modus
        if (useGui) {
            String[] guiArgs = positional.toArray(new String[0]);
            GraphGUI.main(guiArgs);
            return;
        }

        // Dateiname muss angegeben sein
        if (positional.isEmpty()) {
            System.err.println("Fehler: Kein Dateiname angegeben.");
            printUsage();
            return;
        }

        String filePath = positional.get(0);
        Graph g;

        try {
            g = CSVParser.parse(filePath, directed);
        } catch (Exception ex) {
            System.err.println("Fehler beim Einlesen: " + ex.getMessage());
            return;
        }

        GraphAnalyzer analyzer = new GraphAnalyzer(g);

        // ── Dijkstra von einem Startknoten ──────────────────────────────
        if (positional.size() >= 2) {
            String startLabel = positional.get(1);
            int startIdx = findNodeIndex(g, startLabel);
            if (startIdx == -1) {
                System.err.println("Knoten '" + startLabel + "' nicht gefunden.");
                return;
            }

            if (positional.size() >= 3) {
                // Pfad von A nach B
                String endLabel = positional.get(2);
                int endIdx = findNodeIndex(g, endLabel);
                if (endIdx == -1) {
                    System.err.println("Knoten '" + endLabel + "' nicht gefunden.");
                    return;
                }
                DijkstraResult result = g.dijkstra(startIdx);
                double dist = result.getDistTo(endIdx);
                List<Integer> path = result.getPathTo(endIdx);
                System.out.println("Kürzester Pfad von " + startLabel + " nach " + endLabel + ":");
                if (path.isEmpty()) {
                    System.out.println("  Kein Pfad vorhanden!");
                } else {
                    StringBuilder sb = new StringBuilder("  ");
                    for (int i = 0; i < path.size(); i++) {
                        if (i > 0) sb.append(" → ");
                        sb.append(g.getLabel(path.get(i)));
                    }
                    System.out.println(sb);
                    System.out.println("  Distanz: " + (int) dist);
                }
            } else {
                // Dijkstra vom Startknoten zu allen anderen
                System.out.println(analyzer.dijkstraReport(startIdx));
            }
            return;
        }

        // ── Standard: vollständige Analyse ──────────────────────────────
        System.out.println(analyzer.fullReport());
    }

    private static int findNodeIndex(Graph g, String label) {
        for (int i = 0; i < g.getN(); i++) {
            if (g.getLabel(i).equalsIgnoreCase(label)) return i;
        }
        // Auch als Zahl versuchen
        try {
            int idx = Integer.parseInt(label);
            if (idx >= 0 && idx < g.getN()) return idx;
        } catch (NumberFormatException ignored) {}
        return -1;
    }

    private static void printUsage() {
        System.out.println("""
                ┌─────────────────────────────────────────────────────────┐
                │               Graphenanalyzer – Hilfe                   │
                └─────────────────────────────────────────────────────────┘
                
                Verwendung:
                  java graph.Main [OPTIONEN] <datei.csv> [START] [ZIEL]
                
                Optionen:
                  --gui        Grafische Benutzeroberfläche starten
                  --directed   Graph als gerichtet interpretieren
                
                Beispiele:
                  java graph.Main graph.csv               Vollanalyse
                  java graph.Main graph.csv A             Dijkstra von A
                  java graph.Main graph.csv A G           Pfad von A nach G
                  java graph.Main --directed graph.csv    Gerichteter Graph
                  java graph.Main --gui graph.csv         GUI mit Datei
                """);
    }
}
