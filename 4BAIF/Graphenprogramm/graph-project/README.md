# Graphenanalyzer – Java-Abgabeprojekt

## Übersicht
Vollständige Java-Implementierung eines Graphenanalyzers mit GUI und Kommandozeilenunterstützung.
Keine externen Graph-Frameworks – alles selbst implementiert.

---

## Projektstruktur
```
graph-project/
├── src/main/java/graph/
│   ├── Graph.java           – Graphklasse + alle Algorithmen
│   ├── GraphAnalyzer.java   – Formatierte Analyse-Ausgaben
│   ├── CSVParser.java       – CSV-Einlesen (Semikolon/Komma, mit/ohne Header)
│   ├── DijkstraResult.java  – Ergebnisobjekt für Dijkstra
│   ├── GraphGUI.java        – Swing-GUI
│   └── Main.java            – CLI-Einstiegspunkt
├── resources/
│   ├── graph_example.csv    – Beispielgraph (gewichtet, 7 Knoten)
│   └── graph_simple.csv     – Einfacher ungewichteter Graph
└── run.sh                   – Kompilier- und Startskript
```

---

## Kompilieren & Starten

### Voraussetzung: Java JDK 17+ installiert

```bash
# Kompilieren
mkdir -p out
javac -d out src/main/java/graph/*.java

# CLI – vollständige Analyse
java -cp out graph.Main resources/graph_example.csv

# CLI – Dijkstra von Knoten A
java -cp out graph.Main resources/graph_example.csv A

# CLI – kürzester Pfad von A nach G
java -cp out graph.Main resources/graph_example.csv A G

# CLI – gerichteter Graph
java -cp out graph.Main --directed resources/graph_example.csv

# GUI starten
java -cp out graph.Main --gui

# GUI mit Datei starten
java -cp out graph.Main --gui resources/graph_example.csv

# Oder mit dem Skript (Linux/Mac):
chmod +x run.sh
./run.sh resources/graph_example.csv
./run.sh --gui
```

---

## Implementierte Algorithmen

### Minimalanforderungen ✅
| Feature | Klasse / Methode |
|---|---|
| CSV einlesen (`;`, `\n`) | `CSVParser.parse()` |
| Distanzen aller Knoten | `Graph.floydWarshall()` |
| Exzentrizitäten | `Graph.computeEccentricities()` |
| Radius, Durchmesser | `Graph.computeRadius/Diameter()` |
| Zentrum | `Graph.computeCenter()` |

### Erweiterungen (für bessere Note)
| Feature | Klasse / Methode |
|---|---|
| Zusammenhangskomponenten | `Graph.findComponents()` |
| Artikulationspunkte (Tarjan) | `Graph.findArticulations()` |
| Brücken | `Graph.findBridges()` |
| Eulersche Linie / Zyklus (Hierholzer) | `Graph.findEulerPath/Cycle()` |
| Spannbaum (Prim) | `Graph.primSpanningTree()` |
| Breitensuche BFS | `Graph.bfs()` |
| Tiefensuche DFS | `Graph.dfs()` |
| Dijkstra | `Graph.dijkstra()` |
| Starke Zusammenhangskomponenten (Kosaraju) | `Graph.kosarajuSCC()` |
| Grafische Benutzeroberfläche | `GraphGUI` |

---

## CSV-Format

Die Adjazenzmatrix kann mit oder ohne Header eingelesen werden.  
Trennzeichen: `;` (Semikolon) oder `,` (Komma) – wird automatisch erkannt.

### Mit Header-Zeile (empfohlen):
```csv
;A;B;C;D
A;0;4;0;8
B;4;0;8;0
C;0;8;0;7
D;8;0;7;0
```

### Ohne Header (automatische Benennung A, B, C, …):
```csv
0;4;0;8
4;0;8;0
0;8;0;7
8;0;7;0
```

- `0` = keine Kante
- positive Zahl = Kantengewicht (bei ungewichteten Graphen: `1`)
- Zeilenende: `\n` oder `\r\n` (Windows) – beide werden unterstützt

---

## GUI-Funktionen
- **CSV laden** via Dateidialog
- **Gerichtet/Ungerichtet** umschalten
- Vollständige Analyse auf Knopfdruck
- BFS / DFS / Dijkstra mit wählbarem Startknoten
- Eulersche Linie, Spannbaum, Artikulationen, Brücken, SZK
- **Graphdarstellung** als Kreislayout (Canvas)
- Dunkles modernes Design

---

## Beispielausgabe (Kommandozeile)

```
╔══════════════════════════════════════════════════════╗
║            GRAPHENANALYSE – Vollbericht              ║
╚══════════════════════════════════════════════════════╝

▶ GRUNDINFORMATIONEN
  Knotenanzahl : 7
  Kantenanzahl : 9
  Typ          : Ungerichtet
  Knoten       : A, B, C, D, E, F, G

▶ EXZENTRIZITÄTEN
  ecc(A) = 21
  ecc(B) = 17
  ...

▶ KENNZAHLEN
  Radius      = 14
  Durchmesser = 21
  Zentrum     = C, F
```
