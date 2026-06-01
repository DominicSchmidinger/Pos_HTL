package graph;

import java.util.*;

/**
 * Führt alle Graphenanalysen durch und erstellt formatierte Ausgaben.
 * Kann sowohl für die Kommandozeile als auch für die GUI genutzt werden.
 */
public class GraphAnalyzer {

    private final Graph g;

    public GraphAnalyzer(Graph g) {
        this.g = g;
    }

    /**
     * Erstellt einen vollständigen Analysebericht als String.
     *
     * @return Formatierter Analysebericht
     */
    public String fullReport() {
        StringBuilder sb = new StringBuilder();
        int n = g.getN();
        String[] labels = g.getLabels();

        sb.append("╔══════════════════════════════════════════════════════╗\n");
        sb.append("║            GRAPHENANALYSE – Vollbericht              ║\n");
        sb.append("╚══════════════════════════════════════════════════════╝\n\n");

        // ── Grundinformationen ──
        sb.append("▶ GRUNDINFORMATIONEN\n");
        sb.append("  Knotenanzahl : ").append(n).append("\n");
        sb.append("  Kantenanzahl : ").append(countEdges()).append("\n");
        sb.append("  Typ          : ").append(g.isDirected() ? "Gerichtet" : "Ungerichtet").append("\n");
        sb.append("  Knoten       : ").append(String.join(", ", labels)).append("\n\n");

        // ── Adjazenzmatrix ──
        sb.append("▶ ADJAZENZMATRIX\n");
        sb.append(g.toString()).append("\n");

        // ── Distanzmatrix (Floyd-Warshall) ──
        double[][] dist = g.floydWarshall();
        sb.append("▶ DISTANZMATRIX (Floyd-Warshall)\n");
        sb.append(formatDistMatrix(dist)).append("\n");

        // ── Exzentrizitäten ──
        double[] ecc = g.computeEccentricities(dist);
        sb.append("▶ EXZENTRIZITÄTEN\n");
        for (int i = 0; i < n; i++) {
            String eccStr = ecc[i] >= Graph.INF ? "∞ (nicht voll verbunden)" : String.valueOf((int) ecc[i]);
            sb.append(String.format("  ecc(%s) = %s%n", labels[i], eccStr));
        }

        // ── Radius, Durchmesser, Zentrum ──
        double radius = g.computeRadius(ecc);
        double diameter = g.computeDiameter(ecc);
        List<Integer> center = g.computeCenter(ecc);

        sb.append("\n▶ KENNZAHLEN\n");
        sb.append("  Radius     : ").append(radius >= Graph.INF ? "∞" : String.valueOf((int) radius)).append("\n");
        sb.append("  Durchmesser: ").append(diameter == 0 ? "∞" : String.valueOf((int) diameter)).append("\n");
        sb.append("  Zentrum    : ");
        for (int i = 0; i < center.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(labels[center.get(i)]);
        }
        sb.append("\n\n");

        // ── Komponenten ──
        if (!g.isDirected()) {
            List<List<Integer>> comps = g.findComponents();
            sb.append("▶ ZUSAMMENHANGSKOMPONENTEN (").append(comps.size()).append(")\n");
            for (int c = 0; c < comps.size(); c++) {
                sb.append("  Komponente ").append(c + 1).append(": ");
                List<Integer> comp = comps.get(c);
                for (int i = 0; i < comp.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(labels[comp.get(i)]);
                }
                sb.append("\n");
            }
            sb.append("\n");
        }

        // ── Artikulationen ──
        if (!g.isDirected()) {
            List<Integer> arts = g.findArticulations();
            sb.append("▶ ARTIKULATIONSPUNKTE\n");
            if (arts.isEmpty()) {
                sb.append("  Keine Artikulationspunkte gefunden.\n");
            } else {
                sb.append("  ");
                for (int i = 0; i < arts.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(labels[arts.get(i)]);
                }
                sb.append("\n");
            }
            sb.append("\n");
        }

        // ── Brücken ──
        if (!g.isDirected()) {
            List<int[]> bridges = g.findBridges();
            sb.append("▶ BRÜCKEN\n");
            if (bridges.isEmpty()) {
                sb.append("  Keine Brücken gefunden.\n");
            } else {
                for (int[] b : bridges) {
                    sb.append(String.format("  %s — %s%n", labels[b[0]], labels[b[1]]));
                }
            }
            sb.append("\n");
        }

        // ── Eulersche Linie / Zyklus ──
        sb.append("▶ EULERSCHE EIGENSCHAFTEN\n");
        if (g.hasEulerCycle()) {
            sb.append("  ✓ Eulerscher Zyklus existiert.\n");
            List<Integer> path = g.findEulerPath();
            if (path != null) {
                sb.append("    Zyklus: ");
                appendPath(sb, path, labels);
                sb.append("\n");
            }
        } else if (g.hasEulerPath()) {
            sb.append("  ✓ Eulersche Linie existiert.\n");
            List<Integer> path = g.findEulerPath();
            if (path != null) {
                sb.append("    Pfad: ");
                appendPath(sb, path, labels);
                sb.append("\n");
            }
        } else {
            sb.append("  ✗ Kein Eulerscher Pfad / Zyklus.\n");
        }
        sb.append("\n");

        // ── Spannbaum ──
        if (!g.isDirected()) {
            List<int[]> span = g.primSpanningTree();
            sb.append("▶ MINIMALER SPANNBAUM (Prim)\n");
            for (int[] e : span) {
                sb.append(String.format("  %s — %s (Gewicht: %.0f)%n",
                        labels[e[0]], labels[e[1]], g.getWeight(e[0], e[1])));
            }
            sb.append("\n");
        }

        // ── Starke Zusammenhangskomponenten ──
        if (g.isDirected()) {
            List<List<Integer>> sccs = g.kosarajuSCC();
            sb.append("▶ STARKE ZUSAMMENHANGSKOMPONENTEN (Kosaraju) – ").append(sccs.size()).append("\n");
            for (int c = 0; c < sccs.size(); c++) {
                sb.append("  SZK ").append(c + 1).append(": ");
                List<Integer> scc = sccs.get(c);
                for (int i = 0; i < scc.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(labels[scc.get(i)]);
                }
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Dijkstra-Bericht: kürzeste Wege vom Startknoten zu allen anderen.
     */
    public String dijkstraReport(int start) {
        DijkstraResult result = g.dijkstra(start);
        StringBuilder sb = new StringBuilder();
        sb.append("▶ DIJKSTRA-ALGORITHMUS (Start: ").append(g.getLabel(start)).append(")\n");

        for (int v = 0; v < g.getN(); v++) {
            double d = result.getDistTo(v);
            List<Integer> path = result.getPathTo(v);
            String distStr = (d >= Graph.INF) ? "∞" : String.valueOf((int) d);
            sb.append(String.format("  → %s: Distanz = %s", g.getLabel(v), distStr));
            if (!path.isEmpty()) {
                sb.append(", Pfad: ");
                appendPath(sb, path, g.getLabels());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * BFS-Traversierungsbericht.
     */
    public String bfsReport(int start) {
        List<Integer> order = g.bfs(start);
        StringBuilder sb = new StringBuilder();
        sb.append("▶ BREITENSUCHE (BFS) von ").append(g.getLabel(start)).append("\n  Reihenfolge: ");
        appendPath(sb, order, g.getLabels());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * DFS-Traversierungsbericht.
     */
    public String dfsReport(int start) {
        List<Integer> order = g.dfs(start);
        StringBuilder sb = new StringBuilder();
        sb.append("▶ TIEFENSUCHE (DFS) von ").append(g.getLabel(start)).append("\n  Reihenfolge: ");
        appendPath(sb, order, g.getLabels());
        sb.append("\n");
        return sb.toString();
    }

    // ── Hilfsmethoden ──────────────────────────────────────────────────────

    private String formatDistMatrix(double[][] dist) {
        int n = g.getN();
        String[] labels = g.getLabels();
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%8s", ""));
        for (String l : labels) sb.append(String.format("%8s", l));
        sb.append("\n");

        for (int i = 0; i < n; i++) {
            sb.append(String.format("%8s", labels[i]));
            for (int j = 0; j < n; j++) {
                String val = (dist[i][j] >= Graph.INF) ? "∞" : String.valueOf((int) dist[i][j]);
                sb.append(String.format("%8s", val));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void appendPath(StringBuilder sb, List<Integer> path, String[] labels) {
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" → ");
            sb.append(labels[path.get(i)]);
        }
    }

    private int countEdges() {
        int count = 0;
        double[][] m = g.getMatrix();
        for (int i = 0; i < g.getN(); i++)
            for (int j = g.isDirected() ? 0 : i + 1; j < g.getN(); j++)
                if (m[i][j] != 0) count++;
        return count;
    }
}
