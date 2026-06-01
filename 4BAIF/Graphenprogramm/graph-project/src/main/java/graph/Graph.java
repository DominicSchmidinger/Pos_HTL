package graph;

import java.util.*;

/**
 * Repräsentiert einen gewichteten oder ungewichteten Graphen.
 * Unterstützt gerichtete und ungerichtete Graphen.
 * Intern wird die Adjazenzmatrix verwendet.
 */
public class Graph {

    /** Adjazenzmatrix: matrix[i][j] = Kantengewicht (0 = keine Kante, -1 = keine Verbindung) */
    private final double[][] matrix;

    /** Knotenbezeichnungen (aus CSV-Header oder automatisch erzeugt) */
    private final String[] labels;

    /** Anzahl der Knoten */
    private final int n;

    /** Gibt an, ob der Graph gerichtet ist */
    private final boolean directed;

    /** Unendlich-Wert für fehlende Verbindungen */
    public static final double INF = Double.MAX_VALUE / 2;

    /**
     * Erstellt einen Graphen aus einer Adjazenzmatrix.
     *
     * @param matrix   quadratische Adjazenzmatrix
     * @param labels   Knotenbezeichnungen
     * @param directed true = gerichteter Graph
     */
    public Graph(double[][] matrix, String[] labels, boolean directed) {
        this.n = matrix.length;
        this.matrix = matrix;
        this.labels = labels;
        this.directed = directed;
    }

    // =====================================================================
    // GETTER
    // =====================================================================

    public int getN() { return n; }
    public String[] getLabels() { return labels; }
    public double[][] getMatrix() { return matrix; }
    public boolean isDirected() { return directed; }

    public String getLabel(int i) {
        return (labels != null && i < labels.length) ? labels[i] : String.valueOf(i);
    }

    /**
     * Gibt das Gewicht der Kante (i→j) zurück.
     * INF bedeutet: keine Verbindung.
     */
    public double getWeight(int i, int j) {
        double w = matrix[i][j];
        return (w == 0 && i != j) ? INF : w;
    }

    /** Gibt true zurück, wenn zwischen i und j eine Kante existiert */
    public boolean hasEdge(int i, int j) {
        return matrix[i][j] != 0;
    }

    // =====================================================================
    // FLOYD-WARSHALL – kürzeste Wege aller Knotenpaare
    // =====================================================================

    /**
     * Berechnet alle kürzesten Pfade mit dem Floyd-Warshall-Algorithmus.
     *
     * @return 2D-Array dist[i][j] = kürzester Weg von i nach j
     */
    public double[][] floydWarshall() {
        double[][] dist = new double[n][n];

        // Initialisierung: direkte Kantengewichte oder INF
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = getWeight(i, j);
            }
        }

        // Kernschritt: Zwischenknoten k einbeziehen
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        return dist;
    }

    // =====================================================================
    // DIJKSTRA – kürzeste Wege von einem Startknoten
    // =====================================================================

    /**
     * Dijkstra-Algorithmus: berechnet kürzeste Wege vom Startknoten aus.
     *
     * @param start Startknoten-Index
     * @return DijkstraResult mit Distanzen und Vorgänger-Array
     */
    public DijkstraResult dijkstra(int start) {
        double[] dist = new double[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        // Priority Queue: (Distanz, Knoten-Index)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> dist[a[0]]));
        pq.offer(new int[]{start});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                double w = getWeight(u, v);
                if (!visited[v] && w < INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    pq.offer(new int[]{v});
                }
            }
        }
        return new DijkstraResult(dist, prev, start);
    }

    // =====================================================================
    // EXZENTRIZITÄTEN, RADIUS, DURCHMESSER, ZENTRUM
    // =====================================================================

    /**
     * Berechnet Exzentrizität aller Knoten auf Basis der Distanzmatrix.
     * Exzentrizität(v) = max{ d(v, u) | u ∈ V }
     */
    public double[] computeEccentricities(double[][] dist) {
        double[] ecc = new double[n];
        for (int i = 0; i < n; i++) {
            double maxDist = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] < INF) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                } else {
                    // Knoten nicht erreichbar → Exzentrizität = INF
                    maxDist = INF;
                    break;
                }
            }
            ecc[i] = maxDist;
        }
        return ecc;
    }

    /** Radius = min{ ecc(v) | v ∈ V } */
    public double computeRadius(double[] ecc) {
        double radius = INF;
        for (double e : ecc) radius = Math.min(radius, e);
        return radius;
    }

    /** Durchmesser = max{ ecc(v) | v ∈ V } */
    public double computeDiameter(double[] ecc) {
        double diameter = 0;
        for (double e : ecc) {
            if (e < INF) diameter = Math.max(diameter, e);
        }
        return diameter;
    }

    /**
     * Zentrum = alle Knoten mit ecc(v) == Radius
     *
     * @return Liste der Knoten-Indizes im Zentrum
     */
    public List<Integer> computeCenter(double[] ecc) {
        double radius = computeRadius(ecc);
        List<Integer> center = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ecc[i] == radius) center.add(i);
        }
        return center;
    }

    // =====================================================================
    // BREITENSUCHE (BFS)
    // =====================================================================

    /**
     * Breitensuche vom Startknoten.
     *
     * @param start Startknoten-Index
     * @return Liste der besuchten Knoten in BFS-Reihenfolge
     */
    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v = 0; v < n; v++) {
                if (hasEdge(u, v) && !visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        return order;
    }

    // =====================================================================
    // TIEFENSUCHE (DFS)
    // =====================================================================

    /**
     * Tiefensuche vom Startknoten.
     *
     * @param start Startknoten-Index
     * @return Liste der besuchten Knoten in DFS-Reihenfolge
     */
    public List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfsHelper(start, visited, order);
        return order;
    }

    private void dfsHelper(int u, boolean[] visited, List<Integer> order) {
        visited[u] = true;
        order.add(u);
        for (int v = 0; v < n; v++) {
            if (hasEdge(u, v) && !visited[v]) {
                dfsHelper(v, visited, order);
            }
        }
    }

    // =====================================================================
    // ZUSAMMENHANGSKOMPONENTEN
    // =====================================================================

    /**
     * Findet alle Zusammenhangskomponenten (bei ungerichteten Graphen).
     *
     * @return Liste der Komponenten; jede Komponente = Liste von Knoten-Indizes
     */
    public List<List<Integer>> findComponents() {
        boolean[] visited = new boolean[n];
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfsComponent(i, visited, component);
                components.add(component);
            }
        }
        return components;
    }

    private void dfsComponent(int u, boolean[] visited, List<Integer> component) {
        visited[u] = true;
        component.add(u);
        for (int v = 0; v < n; v++) {
            // Ungerichtet: beide Richtungen prüfen
            if ((hasEdge(u, v) || hasEdge(v, u)) && !visited[v]) {
                dfsComponent(v, visited, component);
            }
        }
    }

    // =====================================================================
    // ARTIKULATIONEN UND BRÜCKEN
    // =====================================================================

    /**
     * Findet alle Artikulationspunkte (Knoten, deren Entfernung den Graphen trennt).
     * Algorithmus nach Tarjan mit DFS-Tiefennummer und Low-Link-Werten.
     */
    public List<Integer> findArticulations() {
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];   // Entdeckungszeitpunkt
        int[] low = new int[n];    // Niedrigster erreichbarer disc-Wert
        int[] parent = new int[n];
        boolean[] isArticulation = new boolean[n];
        int[] timer = {0};

        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                articulationDFS(i, visited, disc, low, parent, isArticulation, timer);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isArticulation[i]) result.add(i);
        }
        return result;
    }

    private void articulationDFS(int u, boolean[] visited, int[] disc, int[] low,
                                  int[] parent, boolean[] isArticulation, int[] timer) {
        visited[u] = true;
        disc[u] = low[u] = timer[0]++;
        int childCount = 0;

        for (int v = 0; v < n; v++) {
            if (!hasEdge(u, v) && !hasEdge(v, u)) continue;
            if (!visited[v]) {
                childCount++;
                parent[v] = u;
                articulationDFS(v, visited, disc, low, parent, isArticulation, timer);
                low[u] = Math.min(low[u], low[v]);

                // Wurzel mit mehreren Kindern → Artikulationspunkt
                if (parent[u] == -1 && childCount > 1) isArticulation[u] = true;
                // Nicht-Wurzel: kein Rückwärtsweg über v hinaus
                if (parent[u] != -1 && low[v] >= disc[u]) isArticulation[u] = true;

            } else if (v != parent[u]) {
                // Rückkante: low aktualisieren
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    /**
     * Findet alle Brücken (Kanten, deren Entfernung den Graphen trennt).
     *
     * @return Liste von int[]{u, v} (Kanten)
     */
    public List<int[]> findBridges() {
        boolean[] visited = new boolean[n];
        int[] disc = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        List<int[]> bridges = new ArrayList<>();
        int[] timer = {0};

        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bridgeDFS(i, visited, disc, low, parent, bridges, timer);
            }
        }
        return bridges;
    }

    private void bridgeDFS(int u, boolean[] visited, int[] disc, int[] low,
                            int[] parent, List<int[]> bridges, int[] timer) {
        visited[u] = true;
        disc[u] = low[u] = timer[0]++;

        for (int v = 0; v < n; v++) {
            if (!hasEdge(u, v) && !hasEdge(v, u)) continue;
            if (!visited[v]) {
                parent[v] = u;
                bridgeDFS(v, visited, disc, low, parent, bridges, timer);
                low[u] = Math.min(low[u], low[v]);

                // Keine Rückkante von v aus → Brücke!
                if (low[v] > disc[u]) bridges.add(new int[]{u, v});

            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // =====================================================================
    // EULERSCHE LINIE / EULERSCHER ZYKLUS
    // =====================================================================

    /**
     * Prüft, ob ein Eulerscher Zyklus existiert.
     * Bedingung (ungerichtet): zusammenhängend + alle Knotengrade gerade.
     * Bedingung (gerichtet): stark zusammenhängend + in- == out-Grad für alle Knoten.
     */
    public boolean hasEulerCycle() {
        if (!isConnectedForEuler()) return false;

        if (!directed) {
            // Alle Knotengrade müssen gerade sein
            for (int i = 0; i < n; i++) {
                if (degree(i) % 2 != 0) return false;
            }
            return true;
        } else {
            // In-Grad == Out-Grad für jeden Knoten
            for (int i = 0; i < n; i++) {
                if (inDegree(i) != outDegree(i)) return false;
            }
            return true;
        }
    }

    /**
     * Prüft, ob eine Eulersche Linie (kein Zyklus) existiert.
     * Ungerichtet: genau 2 Knoten mit ungeradem Grad.
     * Gerichtet: genau ein Knoten mit out−in=1 und einer mit in−out=1.
     */
    public boolean hasEulerPath() {
        if (!isConnectedForEuler()) return false;

        if (!directed) {
            int oddCount = 0;
            for (int i = 0; i < n; i++) {
                if (degree(i) % 2 != 0) oddCount++;
            }
            return oddCount == 2;
        } else {
            int startNodes = 0, endNodes = 0;
            for (int i = 0; i < n; i++) {
                int diff = outDegree(i) - inDegree(i);
                if (diff == 1) startNodes++;
                else if (diff == -1) endNodes++;
                else if (diff != 0) return false;
            }
            return startNodes == 1 && endNodes == 1;
        }
    }

    /**
     * Findet einen Eulerschen Pfad/Zyklus mit dem Hierholzer-Algorithmus.
     *
     * @return Liste der Knoten in Euler-Reihenfolge, oder null wenn nicht möglich
     */
    public List<Integer> findEulerPath() {
        if (!hasEulerCycle() && !hasEulerPath()) return null;

        // Kopie der Adjazenzmatrix (wird beim Traversieren verbraucht)
        double[][] tempMatrix = new double[n][n];
        for (int i = 0; i < n; i++) tempMatrix[i] = Arrays.copyOf(matrix[i], n);

        // Startknoten bestimmen
        int start = 0;
        if (!directed) {
            for (int i = 0; i < n; i++) {
                if (degree(i) % 2 != 0) { start = i; break; }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (outDegree(i) - inDegree(i) == 1) { start = i; break; }
            }
        }

        // Hierholzer-Algorithmus
        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> path = new ArrayList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int u = stack.peek();
            boolean found = false;
            for (int v = 0; v < n; v++) {
                if (tempMatrix[u][v] != 0) {
                    stack.push(v);
                    tempMatrix[u][v] = 0;
                    if (!directed) tempMatrix[v][u] = 0;
                    found = true;
                    break;
                }
            }
            if (!found) {
                path.add(0, stack.pop());
            }
        }
        return path;
    }

    // =====================================================================
    // SPANNBAUM (Prim's Algorithmus)
    // =====================================================================

    /**
     * Findet einen minimalen Spannbaum mit dem Prim-Algorithmus.
     *
     * @return Liste der Kanten [u, v] im Spannbaum
     */
    public List<int[]> primSpanningTree() {
        boolean[] inTree = new boolean[n];
        double[] minWeight = new double[n];
        int[] parent = new int[n];

        Arrays.fill(minWeight, INF);
        Arrays.fill(parent, -1);
        minWeight[0] = 0;

        List<int[]> edges = new ArrayList<>();

        for (int iter = 0; iter < n; iter++) {
            // Knoten mit geringstem Kantengewicht zur Menge wählen
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!inTree[i] && (u == -1 || minWeight[i] < minWeight[u])) u = i;
            }
            if (u == -1 || minWeight[u] == INF) break;

            inTree[u] = true;
            if (parent[u] != -1) edges.add(new int[]{parent[u], u});

            // Nachbarn aktualisieren
            for (int v = 0; v < n; v++) {
                double w = getWeight(u, v);
                if (!inTree[v] && w < minWeight[v]) {
                    minWeight[v] = w;
                    parent[v] = u;
                }
            }
        }
        return edges;
    }

    // =====================================================================
    // STARKE ZUSAMMENHANGSKOMPONENTEN (Kosaraju)
    // =====================================================================

    /**
     * Berechnet starke Zusammenhangskomponenten mit dem Kosaraju-Algorithmus.
     * Nur sinnvoll für gerichtete Graphen.
     *
     * @return Liste der Komponenten
     */
    public List<List<Integer>> kosarajuSCC() {
        // Schritt 1: DFS auf originalem Graphen, Knoten in Abschluss-Reihenfolge stapeln
        boolean[] visited = new boolean[n];
        Deque<Integer> finishOrder = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfsFinish(i, visited, finishOrder);
        }

        // Schritt 2: Transponierter Graph erstellen
        double[][] transposed = transpose();

        // Schritt 3: DFS auf transponiertem Graph in umgekehrter Abschluss-Reihenfolge
        Arrays.fill(visited, false);
        List<List<Integer>> sccs = new ArrayList<>();

        while (!finishOrder.isEmpty()) {
            int u = finishOrder.pop();
            if (!visited[u]) {
                List<Integer> scc = new ArrayList<>();
                dfsOnTransposed(u, visited, scc, transposed);
                sccs.add(scc);
            }
        }
        return sccs;
    }

    private void dfsFinish(int u, boolean[] visited, Deque<Integer> stack) {
        visited[u] = true;
        for (int v = 0; v < n; v++) {
            if (hasEdge(u, v) && !visited[v]) dfsFinish(v, visited, stack);
        }
        stack.push(u);
    }

    private double[][] transpose() {
        double[][] t = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                t[j][i] = matrix[i][j];
        return t;
    }

    private void dfsOnTransposed(int u, boolean[] visited, List<Integer> scc, double[][] t) {
        visited[u] = true;
        scc.add(u);
        for (int v = 0; v < n; v++) {
            if (t[u][v] != 0 && !visited[v]) dfsOnTransposed(v, visited, scc, t);
        }
    }

    // =====================================================================
    // HILFS-METHODEN
    // =====================================================================

    /** Grad eines Knotens (ungerichtet: Summe aller Verbindungen) */
    public int degree(int u) {
        int d = 0;
        for (int v = 0; v < n; v++) if (hasEdge(u, v) || hasEdge(v, u)) d++;
        return d;
    }

    /** Aus-Grad eines Knotens (gerichtet) */
    public int outDegree(int u) {
        int d = 0;
        for (int v = 0; v < n; v++) if (hasEdge(u, v)) d++;
        return d;
    }

    /** Ein-Grad eines Knotens (gerichtet) */
    public int inDegree(int u) {
        int d = 0;
        for (int v = 0; v < n; v++) if (hasEdge(v, u)) d++;
        return d;
    }

    /** Prüft Zusammenhang für Euler-Tests (ignoriert isolierte Knoten) */
    private boolean isConnectedForEuler() {
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (directed ? outDegree(i) > 0 : degree(i) > 0) { start = i; break; }
        }
        if (start == -1) return false;

        boolean[] visited = new boolean[n];
        dfsHelper(start, visited, new ArrayList<>());

        // Alle Knoten mit Kanten müssen besucht worden sein
        for (int i = 0; i < n; i++) {
            if (!visited[i] && degree(i) > 0) return false;
        }
        return true;
    }

    /** Erzeugt eine lesbare Darstellung der Adjazenzmatrix */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%8s", ""));
        for (String l : labels) sb.append(String.format("%8s", l));
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%8s", labels[i]));
            for (int j = 0; j < n; j++) {
                double w = matrix[i][j];
                sb.append(String.format("%8s", w == 0 ? "-" : String.valueOf((int)w)));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
