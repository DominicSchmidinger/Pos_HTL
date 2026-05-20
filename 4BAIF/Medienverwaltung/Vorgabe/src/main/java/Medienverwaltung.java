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

    public boolean aufnehmen(Medium medium){
        // TODO: null und Duplikate prüfen
        if (medium == null || medien.contains(medium)){
            throw new MedienException("Bro das gibt es schon oda du hast nix reingeschrieben");
        }
        medien.add(medium);
        return true;
    }

    public Medium getMedium(int index) {
        if (index < 0 || index >= medien.size()) {
            return null;
        }
        return medien.get(index);
    }

    public double berechneGesamtbeliebtheit() {
        double summe = 0.0;
        for (Medium medium : medien) {
            summe += medium.berechneBeliebtheit();
        }
        return summe;
    }

    public int zaehleBuecher() {
        if (medien.isEmpty()) {
            return -99;
        }

        int anzahl = 0;
        for (Medium medium : medien) {
            if (medium instanceof Buch) {
                anzahl++;
            }
        }
        return anzahl;
    }

    public int summeFilmdauer() {
        if (medien.isEmpty()) {
            return -99;
        }

        int summe = 0;
        for (Medium medium : medien) {
            if (medium instanceof Film) {
                Film film = (Film) medium;
                summe += film.getDauerMinuten();
            }
        }
        return summe;
    }

    public int entferneAlleBuecherUnterSeiten(int minSeiten) {
        int anzahl = 0;
        Iterator<Medium> iterator = medien.iterator();
        while (iterator.hasNext()) {
            Medium medium = iterator.next();
            if (medium instanceof Buch) {
                Buch buch = (Buch) medium;
                if (buch.getSeiten() < minSeiten) {
                    iterator.remove();
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    public List<Medium> filtere(MedienFilter filter) {
        List<Medium> ergebnis = new LinkedList<>();
        // TODO: mit MedienFilter filtern
        return ergebnis;
    }

    public int zaehleMitFilter(MedienFilter filter) {
        // TODO: mit einem MedienFilter passende Medien zaehlen
        return 0;
    }

    public void readFromCsv() {
        // TODO: Medien aus INPUT_FILEPATH lesen
        // Fehlerhafte Zeilen sollen abgefangen und übersprungen werden.
    }

    private Medium parseMedium(String line) throws MedienException {
        // TODO: CSV-Zeile selbst in Buch oder Film umwandeln.
        return null;
    }

    public void writeToCsv() {
        // TODO: alle Medien in OUTPUT_FILEPATH schreiben
    }

    public void writeReversedToCsv() {
        // TODO: alle Medien in umgekehrter Reihenfolge in REVERSED_OUTPUT_FILEPATH schreiben
    }

    public void writePopularToCsv(MedienFilter filter) {
        // TODO: nur Medien in POPULAR_OUTPUT_FILEPATH schreiben, die vom Filter akzeptiert werden
    }

    public void writeFilmeToCsv(MedienFilter filter) {
        // TODO: nur Medien in FILME_OUTPUT_FILEPATH schreiben, die vom Filter akzeptiert werden
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Medienverwaltung:\n");
        if (medien.isEmpty()) {
            builder.append("Keine Medien vorhanden\n");
        } else {
            for (Medium medium : medien) {
                builder.append(medium).append("\n");
            }
        }
        return builder.toString();
    }
}
