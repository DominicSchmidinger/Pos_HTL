package graph;

import java.util.*;

/**
 * Ergebnisobjekt des Dijkstra-Algorithmus.
 * Enthält Distanzen vom Startknoten und Vorgänger-Array zur Pfadrekonstruktion.
 */
public class DijkstraResult {

    /** Distanzarray: dist[v] = kürzeste Distanz vom Start zu v */
    private final double[] dist;

    /** Vorgänger-Array: prev[v] = Vorgänger von v auf dem kürzesten Pfad */
    private final int[] prev;

    /** Startknoten-Index */
    private final int start;

    public DijkstraResult(double[] dist, int[] prev, int start) {
        this.dist = dist;
        this.prev = prev;
        this.start = start;
    }

    public double[] getDist() { return dist; }
    public int[] getPrev() { return prev; }
    public int getStart() { return start; }

    public double getDistTo(int v) { return dist[v]; }

    /**
     * Rekonstruiert den kürzesten Pfad von Start zu Ziel.
     *
     * @param target Zielknoten-Index
     * @return Liste der Knoten-Indizes auf dem Pfad (inkl. Start und Ziel), oder leer wenn kein Pfad
     */
    public List<Integer> getPathTo(int target) {
        List<Integer> path = new ArrayList<>();
        if (dist[target] == Graph.INF) return path; // kein Pfad vorhanden

        for (int v = target; v != -1; v = prev[v]) {
            path.add(0, v);
        }
        return path;
    }
}
