import java.io.*;
import java.time.Year;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Medienverwaltung {
    private static final String INPUT_FILEPATH = "src/main/resources/medien.csv";
    private static final String OUTPUT_FILEPATH = "src/main/resources/medien_output.csv";
    private static final String REVERSED_OUTPUT_FILEPATH = "src/main/resources/medien_output_reversed.csv";
    private static final String POPULAR_OUTPUT_FILEPATH = "src/main/resources/medien_output_popular.csv";
    private static final String FILME_OUTPUT_FILEPATH = "src/main/resources/medien_output_filme.csv";

    private List<Medium> medien;

    public Medienverwaltung() {
        medien = new LinkedList<>();
    }

    public boolean aufnehmen(Medium medium) {
        if (medium == null || medien.contains(medium))
            throw new MedienException("Medium ist null oder bereits vorhanden");
        medien.add(medium);
        return true;
    }

    public Medium getMedium(int index) {
        if (index < 0 || index >= medien.size()) return null;
        return medien.get(index);
    }

    public double berechneGesamtbeliebtheit() {
        double summe = 0.0;
        for (Medium medium : medien)
            summe += medium.berechneBeliebtheit();
        return summe;
    }

    public int zaehleBuecher() {
        if (medien.isEmpty()) return -99;
        int anzahl = 0;
        for (Medium medium : medien)
            if (medium instanceof Buch) anzahl++;
        return anzahl;
    }

    public int summeFilmdauer() {
        if (medien.isEmpty()) return -99;
        int summe = 0;
        for (Medium medium : medien)
            if (medium instanceof Film)
                summe += ((Film) medium).getDauerMinuten();
        return summe;
    }

    public int entferneAlleBuecherUnterSeiten(int minSeiten) {
        int anzahl = 0;
        Iterator<Medium> iterator = medien.iterator();
        while (iterator.hasNext()) {
            Medium medium = iterator.next();
            if (medium instanceof Buch && ((Buch) medium).getSeiten() < minSeiten) {
                iterator.remove();
                anzahl++;
            }
        }
        return anzahl;
    }

    public List<Medium> filtere(MedienFilter filter) {
        List<Medium> ergebnis = new LinkedList<>();
        for (Medium medium : medien)
            if (filter.akzeptiert(medium))
                ergebnis.add(medium);
        return ergebnis;
    }

    public int zaehleMitFilter(MedienFilter filter) {
        int summe = 0;
        for (Medium medium : medien)
            if (filter.akzeptiert(medium))
                summe++;
        return summe;
    }

    public void readFromCsv() {
        // FIX: parseMedium() und aufnehmen() werden jetzt tatsächlich aufgerufen
        // FIX: Konstante statt hardcodiertem String
        // FIX: line = br.readLine() direkt im while, kein Extra-readLine() davor
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILEPATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    aufnehmen(parseMedium(line));
                } catch (MedienException e) {
                    System.out.println("Fehlerhafte Zeile übersprungen: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen: " + e.getMessage());
        }
    }

    private Medium parseMedium(String line) throws MedienException {
        if (line == null || line.isBlank())
            throw new MedienException("Leere Zeile");

        String[] teile = line.split(";");
        if (teile.length != 5)
            throw new MedienException("Falsche Spaltenanzahl: " + teile.length);

        String typ   = teile[0].trim();
        String titel = teile[1].trim();

        try {
            Year jahr     = Year.parse(teile[2].trim());
            int bewertung = Integer.parseInt(teile[3].trim());
            int letzteZahl = Integer.parseInt(teile[4].trim());

            if (typ.equals("Buch"))
                return new Buch(titel, jahr, bewertung, letzteZahl);
            else if (typ.equals("Film"))
                return new Film(titel, jahr, bewertung, letzteZahl);
            else
                throw new MedienException("Unbekannter Typ: " + typ);

        } catch (NumberFormatException e) {
            throw new MedienException("Ungültige Zahl in Zeile: " + line);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MedienException("Ungültiges Jahr in Zeile: " + line);
        }
    }

    public void writeToCsv() {
        // FIX: throws IOException entfernt, stattdessen catch
        // FIX: "/n" -> writer.newLine()
        // FIX: Konstante statt hardcodiertem String
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILEPATH))) {
            for (Medium medium : medien) {
                writer.write(medium.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }

    public void writeReversedToCsv() {
        // FIX: "/n" + newLine() war doppelt -> nur newLine()
        // FIX: Konstante verwenden
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVERSED_OUTPUT_FILEPATH))) {
            for (int i = medien.size() - 1; i >= 0; i--) {
                writer.write(medien.get(i).toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }

    public void writePopularToCsv(MedienFilter filter) {
        // FIX: Konstante statt hardcodiertem String, unnötige Variable entfernt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(POPULAR_OUTPUT_FILEPATH))) {
            for (Medium medium : medien) {
                if (filter.akzeptiert(medium)) {
                    writer.write(medium.toCsvString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }

    public void writeFilmeToCsv(MedienFilter filter) {
        // FIX: try-Klammer war kaputt, Variablenname war wr aber writer verwendet
        // FIX: Konstante statt hardcodiertem String
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILME_OUTPUT_FILEPATH))) {
            for (Medium medium : medien) {
                if (filter.akzeptiert(medium)) {
                    writer.write(medium.toCsvString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Medienverwaltung:\n");
        if (medien.isEmpty()) {
            builder.append("Keine Medien vorhanden\n");
        } else {
            for (Medium medium : medien)
                builder.append(medium).append("\n");
        }
        return builder.toString();
    }
}