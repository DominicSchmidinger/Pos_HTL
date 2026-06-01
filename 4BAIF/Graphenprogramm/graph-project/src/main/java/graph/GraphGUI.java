package graph;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Grafische Benutzeroberfläche für den Graphenanalyzer.
 *
 * Funktionen:
 * - CSV-Datei laden (Dateidialog)
 * - Vollständige Analyse anzeigen
 * - BFS / DFS / Dijkstra mit wählbarem Startknoten
 * - Graphische Darstellung des Graphen (Canvas)
 * - Gerichtet/Ungerichtet umschalten
 */
public class GraphGUI extends JFrame {

    // ── Aktueller Graph ───────────────────────────────────────────────────
    private Graph currentGraph = null;
    private GraphAnalyzer analyzer = null;

    // ── UI-Komponenten ────────────────────────────────────────────────────
    private JTextArea outputArea;
    private JComboBox<String> nodeSelector;
    private GraphCanvas canvas;
    private JLabel statusLabel;
    private JCheckBox directedCheckbox;
    private JLabel fileLabel;

    public GraphGUI() {
        super("Graphenanalyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        initUI();
    }

    // =====================================================================
    // UI-AUFBAU
    // =====================================================================

    private void initUI() {
        // Modernes dunkles Look & Feel simulieren
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(40, 44, 52));

        add(buildTopBar(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);
    }

    /** Obere Toolbar mit Laden-Button und Optionen */
    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        bar.setBackground(new Color(33, 37, 43));
        bar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(80, 90, 110)));

        // Titel
        JLabel title = new JLabel("🔷 Graphenanalyzer");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(100, 180, 255));
        bar.add(title);

        bar.add(Box.createHorizontalStrut(15));

        // Datei laden
        JButton loadBtn = styledButton("📂 CSV laden", new Color(60, 130, 200));
        loadBtn.addActionListener(e -> loadCSV());
        bar.add(loadBtn);

        // Dateiname-Anzeige
        fileLabel = new JLabel("  Keine Datei geladen");
        fileLabel.setForeground(new Color(150, 160, 180));
        fileLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bar.add(fileLabel);

        bar.add(Box.createHorizontalStrut(20));

        // Gerichtet/Ungerichtet
        directedCheckbox = new JCheckBox("Gerichteter Graph");
        directedCheckbox.setForeground(Color.WHITE);
        directedCheckbox.setBackground(new Color(33, 37, 43));
        directedCheckbox.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bar.add(directedCheckbox);

        return bar;
    }

    /** Hauptbereich: Seitenleiste links, Canvas + Output rechts */
    private JSplitPane buildCenterPanel() {
        // ── Linke Seitenleiste: Aktionsbuttons ──
        JPanel sidebar = buildSidebar();

        // ── Rechter Bereich: Graph-Canvas oben, Text-Output unten ──
        canvas = new GraphCanvas();
        canvas.setPreferredSize(new Dimension(600, 350));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setBackground(new Color(25, 28, 35));
        outputArea.setForeground(new Color(200, 215, 240));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        outputArea.setText("Willkommen beim Graphenanalyzer!\nBitte laden Sie eine CSV-Datei mit der Adjazenzmatrix.");
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 80, 100)), " Ausgabe ",
                TitledBorder.LEFT, TitledBorder.TOP, null, new Color(150, 170, 200)));

        JSplitPane rightSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, canvas, outputScroll);
        rightSplit.setDividerLocation(320);
        rightSplit.setBackground(new Color(40, 44, 52));

        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, rightSplit);
        mainSplit.setDividerLocation(220);
        mainSplit.setBackground(new Color(40, 44, 52));
        return mainSplit;
    }

    /** Linke Seitenleiste mit allen Analyse-Buttons */
    private JPanel buildSidebar() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(33, 37, 43));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addSectionLabel(panel, "ANALYSE");
        addBtn(panel, "📊 Vollständige Analyse", new Color(70, 160, 80), e -> runFullAnalysis());
        addBtn(panel, "📏 Distanzen & Exzentrizitäten", new Color(60, 140, 80), e -> runDistances());
        addBtn(panel, "🌐 Floyd-Warshall", new Color(50, 120, 70), e -> runFloydWarshall());

        panel.add(Box.createVerticalStrut(15));
        addSectionLabel(panel, "SUCHE");
        addBtn(panel, "🔍 Breitensuche (BFS)", new Color(160, 90, 40), e -> runBFS());
        addBtn(panel, "🔍 Tiefensuche (DFS)", new Color(150, 80, 30), e -> runDFS());
        addBtn(panel, "🗺 Dijkstra", new Color(140, 70, 20), e -> runDijkstra());

        panel.add(Box.createVerticalStrut(15));
        addSectionLabel(panel, "STRUKTUR");
        addBtn(panel, "🔗 Komponenten", new Color(130, 50, 160), e -> runComponents());
        addBtn(panel, "🧩 Artikulationen & Brücken", new Color(120, 40, 150), e -> runArticulationsAndBridges());
        addBtn(panel, "🌀 Eulersche Linie/Zyklus", new Color(110, 30, 140), e -> runEuler());
        addBtn(panel, "🌲 Spannbaum (Prim)", new Color(100, 20, 130), e -> runSpanningTree());
        addBtn(panel, "⬡ Starke ZK (Kosaraju)", new Color(90, 10, 120), e -> runSCC());

        panel.add(Box.createVerticalStrut(15));
        addSectionLabel(panel, "STARTKNOTEN");

        nodeSelector = new JComboBox<>();
        nodeSelector.setBackground(new Color(50, 56, 70));
        nodeSelector.setForeground(Color.WHITE);
        nodeSelector.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nodeSelector.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(nodeSelector);

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.setBackground(new Color(25, 28, 35));
        statusLabel = new JLabel("Bereit.");
        statusLabel.setForeground(new Color(120, 140, 170));
        bar.add(statusLabel);
        return bar;
    }

    // =====================================================================
    // AKTIONEN
    // =====================================================================

    /** Öffnet Dateidialog und lädt den Graphen aus CSV */
    private void loadCSV() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV-Dateien (*.csv)", "csv"));
        chooser.setDialogTitle("Adjazenzmatrix-CSV auswählen");

        if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        boolean directed = directedCheckbox.isSelected();

        try {
            currentGraph = CSVParser.parse(file.getAbsolutePath(), directed);
            analyzer = new GraphAnalyzer(currentGraph);

            // Knotenauswahl aktualisieren
            nodeSelector.removeAllItems();
            for (String label : currentGraph.getLabels()) nodeSelector.addItem(label);

            // Canvas aktualisieren
            canvas.setGraph(currentGraph);

            fileLabel.setText("  " + file.getName());
            setStatus("✓ Graph geladen: " + currentGraph.getN() + " Knoten aus " + file.getName());
            output("Graph erfolgreich geladen: " + file.getAbsolutePath() + "\n"
                    + currentGraph.getN() + " Knoten, " + (directed ? "gerichtet" : "ungerichtet") + "\n\n"
                    + currentGraph.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Laden:\n" + ex.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
            setStatus("✗ Fehler beim Laden: " + ex.getMessage());
        }
    }

    private void runFullAnalysis() {
        if (!checkGraph()) return;
        output(analyzer.fullReport());
        setStatus("Vollständige Analyse abgeschlossen.");
    }

    private void runDistances() {
        if (!checkGraph()) return;
        double[][] dist = currentGraph.floydWarshall();
        double[] ecc = currentGraph.computeEccentricities(dist);
        double radius = currentGraph.computeRadius(ecc);
        double diameter = currentGraph.computeDiameter(ecc);
        List<Integer> center = currentGraph.computeCenter(ecc);
        String[] labels = currentGraph.getLabels();

        StringBuilder sb = new StringBuilder("▶ DISTANZEN & EXZENTRIZITÄTEN\n\n");
        for (int i = 0; i < currentGraph.getN(); i++) {
            String eccStr = ecc[i] >= Graph.INF ? "∞" : String.valueOf((int) ecc[i]);
            sb.append(String.format("  Knoten %-5s  ecc = %s%n", labels[i], eccStr));
        }
        sb.append(String.format("%n  Radius      = %s%n", radius >= Graph.INF ? "∞" : String.valueOf((int) radius)));
        sb.append(String.format("  Durchmesser = %s%n", diameter == 0 ? "∞" : String.valueOf((int) diameter)));
        sb.append("  Zentrum     = ");
        for (int i = 0; i < center.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(labels[center.get(i)]);
        }
        output(sb.toString());
        setStatus("Exzentrizitäten berechnet.");
    }

    private void runFloydWarshall() {
        if (!checkGraph()) return;
        double[][] dist = currentGraph.floydWarshall();
        StringBuilder sb = new StringBuilder("▶ DISTANZMATRIX (Floyd-Warshall)\n\n");
        String[] labels = currentGraph.getLabels();
        int n = currentGraph.getN();

        sb.append(String.format("%8s", ""));
        for (String l : labels) sb.append(String.format("%8s", l));
        sb.append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%8s", labels[i]));
            for (int j = 0; j < n; j++) {
                String val = dist[i][j] >= Graph.INF ? "∞" : String.valueOf((int) dist[i][j]);
                sb.append(String.format("%8s", val));
            }
            sb.append("\n");
        }
        output(sb.toString());
        setStatus("Floyd-Warshall berechnet.");
    }

    private void runBFS() {
        if (!checkGraph()) return;
        int start = nodeSelector.getSelectedIndex();
        output(analyzer.bfsReport(start));
        setStatus("BFS abgeschlossen.");
    }

    private void runDFS() {
        if (!checkGraph()) return;
        int start = nodeSelector.getSelectedIndex();
        output(analyzer.dfsReport(start));
        setStatus("DFS abgeschlossen.");
    }

    private void runDijkstra() {
        if (!checkGraph()) return;
        int start = nodeSelector.getSelectedIndex();
        output(analyzer.dijkstraReport(start));
        setStatus("Dijkstra abgeschlossen.");
    }

    private void runComponents() {
        if (!checkGraph()) return;
        List<List<Integer>> comps = currentGraph.findComponents();
        String[] labels = currentGraph.getLabels();
        StringBuilder sb = new StringBuilder("▶ ZUSAMMENHANGSKOMPONENTEN (" + comps.size() + ")\n\n");
        for (int c = 0; c < comps.size(); c++) {
            sb.append("  Komponente ").append(c + 1).append(": ");
            List<Integer> comp = comps.get(c);
            for (int i = 0; i < comp.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(labels[comp.get(i)]);
            }
            sb.append("\n");
        }
        output(sb.toString());
        setStatus("Komponenten berechnet: " + comps.size());
    }

    private void runArticulationsAndBridges() {
        if (!checkGraph()) return;
        String[] labels = currentGraph.getLabels();
        StringBuilder sb = new StringBuilder("▶ ARTIKULATIONSPUNKTE UND BRÜCKEN\n\n");

        List<Integer> arts = currentGraph.findArticulations();
        sb.append("Artikulationspunkte: ");
        if (arts.isEmpty()) sb.append("Keine");
        else for (int i = 0; i < arts.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(labels[arts.get(i)]);
        }
        sb.append("\n\n");

        List<int[]> bridges = currentGraph.findBridges();
        sb.append("Brücken:\n");
        if (bridges.isEmpty()) sb.append("  Keine");
        else for (int[] b : bridges)
            sb.append(String.format("  %s — %s%n", labels[b[0]], labels[b[1]]));

        output(sb.toString());
        setStatus("Artikulationen & Brücken berechnet.");
    }

    private void runEuler() {
        if (!checkGraph()) return;
        StringBuilder sb = new StringBuilder("▶ EULERSCHE EIGENSCHAFTEN\n\n");
        if (currentGraph.hasEulerCycle()) {
            sb.append("✓ Eulerscher Zyklus existiert.\n");
            List<Integer> path = currentGraph.findEulerPath();
            if (path != null) {
                sb.append("  Zyklus: ");
                appendPath(sb, path, currentGraph.getLabels());
            }
        } else if (currentGraph.hasEulerPath()) {
            sb.append("✓ Eulersche Linie (kein Zyklus) existiert.\n");
            List<Integer> path = currentGraph.findEulerPath();
            if (path != null) {
                sb.append("  Pfad: ");
                appendPath(sb, path, currentGraph.getLabels());
            }
        } else {
            sb.append("✗ Kein Eulerscher Pfad/Zyklus vorhanden.\n");
            sb.append("  Bedingung verletzt: nicht alle Knoten haben geraden Grad.\n");
        }
        output(sb.toString());
        setStatus("Euler-Test abgeschlossen.");
    }

    private void runSpanningTree() {
        if (!checkGraph()) return;
        List<int[]> edges = currentGraph.primSpanningTree();
        String[] labels = currentGraph.getLabels();
        StringBuilder sb = new StringBuilder("▶ MINIMALER SPANNBAUM (Prim)\n\n");
        double total = 0;
        for (int[] e : edges) {
            double w = currentGraph.getWeight(e[0], e[1]);
            total += w;
            sb.append(String.format("  %s — %s  (Gewicht: %.0f)%n", labels[e[0]], labels[e[1]], w));
        }
        sb.append(String.format("%nGesamtgewicht: %.0f%n", total));
        output(sb.toString());
        setStatus("Spannbaum berechnet.");
    }

    private void runSCC() {
        if (!checkGraph()) return;
        List<List<Integer>> sccs = currentGraph.kosarajuSCC();
        String[] labels = currentGraph.getLabels();
        StringBuilder sb = new StringBuilder("▶ STARKE ZUSAMMENHANGSKOMPONENTEN (Kosaraju) – " + sccs.size() + "\n\n");
        for (int c = 0; c < sccs.size(); c++) {
            sb.append("  SZK ").append(c + 1).append(": ");
            List<Integer> scc = sccs.get(c);
            for (int i = 0; i < scc.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(labels[scc.get(i)]);
            }
            sb.append("\n");
        }
        output(sb.toString());
        setStatus("Starke Zusammenhangskomponenten: " + sccs.size());
    }

    // =====================================================================
    // HILFSMETHODEN
    // =====================================================================

    private void output(String text) {
        outputArea.setText(text);
        outputArea.setCaretPosition(0);
    }

    private void setStatus(String text) {
        statusLabel.setText("  " + text);
    }

    private boolean checkGraph() {
        if (currentGraph == null) {
            JOptionPane.showMessageDialog(this,
                    "Bitte zuerst eine CSV-Datei laden!",
                    "Kein Graph geladen", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void appendPath(StringBuilder sb, List<Integer> path, String[] labels) {
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(" → ");
            sb.append(labels[path.get(i)]);
        }
    }

    private JButton styledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void addBtn(JPanel panel, String text, Color color, ActionListener action) {
        JButton btn = styledButton(text, color);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        btn.addActionListener(action);
        panel.add(btn);
        panel.add(Box.createVerticalStrut(4));
    }

    private void addSectionLabel(JPanel panel, String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(new Color(100, 120, 160));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(4, 0, 2, 0));
        panel.add(lbl);
    }

    // =====================================================================
    // GRAPHEN-CANVAS – visuelle Darstellung
    // =====================================================================

    /**
     * Innere Klasse: zeichnet den Graphen als Kreislayout auf einem Canvas.
     */
    static class GraphCanvas extends JPanel {
        private Graph graph;

        // Farben
        private static final Color BG        = new Color(20, 23, 30);
        private static final Color EDGE      = new Color(80, 100, 140);
        private static final Color NODE_FILL = new Color(60, 120, 200);
        private static final Color NODE_BORDER = new Color(100, 170, 255);
        private static final Color TEXT      = Color.WHITE;
        private static final Color WEIGHT    = new Color(200, 180, 100);

        public GraphCanvas() {
            setBackground(BG);
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(70, 80, 100)), " Graphdarstellung ",
                    TitledBorder.LEFT, TitledBorder.TOP, null, new Color(150, 170, 200)));
        }

        public void setGraph(Graph g) {
            this.graph = g;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g2d) {
            super.paintComponent(g2d);
            if (graph == null) {
                g2d.setColor(new Color(80, 90, 110));
                g2d.setFont(new Font("SansSerif", Font.ITALIC, 14));
                g2d.drawString("Kein Graph geladen", getWidth() / 2 - 70, getHeight() / 2);
                return;
            }

            Graphics2D g = (Graphics2D) g2d;
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int n = graph.getN();
            int padding = 50;
            int w = getWidth() - 2 * padding;
            int h = getHeight() - 2 * padding;
            int r = 20; // Knotenradius

            // Knotenpositionen im Kreislayout berechnen
            int[] cx = new int[n];
            int[] cy = new int[n];
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int circleR = Math.min(w, h) / 2;

            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n - Math.PI / 2;
                cx[i] = (int) (centerX + circleR * Math.cos(angle));
                cy[i] = (int) (centerY + circleR * Math.sin(angle));
            }

            // Kanten zeichnen
            g.setStroke(new BasicStroke(1.5f));
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph.hasEdge(i, j)) {
                        g.setColor(EDGE);
                        g.drawLine(cx[i], cy[i], cx[j], cy[j]);

                        // Kantengewicht anzeigen (wenn > 1)
                        double w2 = graph.getWeight(i, j);
                        if (w2 > 1 && w2 < Graph.INF) {
                            g.setColor(WEIGHT);
                            g.setFont(new Font("SansSerif", Font.PLAIN, 10));
                            g.drawString(String.valueOf((int) w2),
                                    (cx[i] + cx[j]) / 2, (cy[i] + cy[j]) / 2);
                        }

                        // Pfeil für gerichteten Graph
                        if (graph.isDirected()) {
                            drawArrow(g, cx[i], cy[i], cx[j], cy[j], r);
                        }
                    }
                }
            }

            // Knoten zeichnen
            for (int i = 0; i < n; i++) {
                // Schatten
                g.setColor(new Color(0, 0, 0, 80));
                g.fillOval(cx[i] - r + 2, cy[i] - r + 2, 2 * r, 2 * r);

                // Füllfarbe
                g.setColor(NODE_FILL);
                g.fillOval(cx[i] - r, cy[i] - r, 2 * r, 2 * r);

                // Rahmen
                g.setColor(NODE_BORDER);
                g.setStroke(new BasicStroke(2f));
                g.drawOval(cx[i] - r, cy[i] - r, 2 * r, 2 * r);

                // Knotenname
                g.setColor(TEXT);
                g.setFont(new Font("SansSerif", Font.BOLD, 12));
                FontMetrics fm = g.getFontMetrics();
                String label = graph.getLabel(i);
                g.drawString(label,
                        cx[i] - fm.stringWidth(label) / 2,
                        cy[i] + fm.getAscent() / 2 - 1);
            }
        }

        /** Zeichnet einen Pfeil von (x1,y1) nach (x2,y2), endet am Knotenrand */
        private void drawArrow(Graphics2D g, int x1, int y1, int x2, int y2, int nodeR) {
            double dx = x2 - x1, dy = y2 - y1;
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len == 0) return;
            dx /= len; dy /= len;

            // Endpunkt an Knotenrand
            int ex = (int) (x2 - dx * nodeR);
            int ey = (int) (y2 - dy * nodeR);

            // Pfeilspitze
            int size = 8;
            double ax = ex - size * dx + size / 2.0 * dy;
            double ay = ey - size * dy - size / 2.0 * dx;
            double bx = ex - size * dx - size / 2.0 * dy;
            double by = ey - size * dy + size / 2.0 * dx;

            g.setColor(EDGE);
            g.fillPolygon(
                    new int[]{ex, (int) ax, (int) bx},
                    new int[]{ey, (int) ay, (int) by}, 3);
        }
    }

    // =====================================================================
    // MAIN – Einstiegspunkt
    // =====================================================================

    /**
     * Einstiegspunkt. Optionaler Dateiname als Argument möglich.
     * Beispiel: java graph.GraphGUI graph.csv
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphGUI gui = new GraphGUI();
            gui.setVisible(true);

            // Datei aus Kommandozeilenargument laden, falls angegeben
            if (args.length > 0) {
                File f = new File(args[0]);
                if (f.exists()) {
                    try {
                        boolean directed = false;
                        gui.currentGraph = CSVParser.parse(f.getAbsolutePath(), directed);
                        gui.analyzer = new GraphAnalyzer(gui.currentGraph);
                        gui.canvas.setGraph(gui.currentGraph);
                        gui.nodeSelector.removeAllItems();
                        for (String l : gui.currentGraph.getLabels()) gui.nodeSelector.addItem(l);
                        gui.fileLabel.setText("  " + f.getName());
                        gui.setStatus("✓ Datei geladen: " + f.getName());
                        gui.output(gui.currentGraph.toString());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(gui, "Fehler: " + ex.getMessage());
                    }
                }
            }
        });
    }
}
