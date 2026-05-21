import java.time.Year;

public class Main {
    public static void main(String[] args) {
        testObjekteUndAufnehmen();
        testCsvImportExport();
        testLambdaFilter();
    }

    private static void testObjekteUndAufnehmen() {
        System.out.println("== Objekte und Aufnehmen ==");
        try {
            Medienverwaltung verwaltung = new Medienverwaltung();
            verwaltung.aufnehmen(new Buch("Java Schritt für Schritt", Year.of(2025), 18, 320));
            verwaltung.aufnehmen(new Film("Code Stories", Year.of(2026), 42, 95));

            System.out.println(verwaltung);
            System.out.println("Gesamtbeliebtheit: " + verwaltung.berechneGesamtbeliebtheit());
            System.out.println("Bücher: " + verwaltung.zaehleBuecher());
            System.out.println("Filmdauer: " + verwaltung.summeFilmdauer());
        } catch (MedienException e) {
            System.out.println("Unerwarteter Fehler: " + e.getMessage());
        }
    }

    private static void testCsvImportExport() {
        // FIX: throws IOException entfernt (writeToCsv wirft keine mehr)
        System.out.println("== CSV Import/Export ==");
        Medienverwaltung verwaltung = new Medienverwaltung();
        verwaltung.readFromCsv();

        System.out.println(verwaltung);
        verwaltung.writeToCsv();
        verwaltung.writeReversedToCsv();
    }

    private static void testLambdaFilter() {
        // FIX: doppelte Methode entfernt, nur eine Version
        System.out.println("== Lambda und Filter ==");
        Medienverwaltung verwaltung = new Medienverwaltung();
        verwaltung.readFromCsv();

        MedienFilter beliebt   = medium -> medium.berechneBeliebtheit() > 50.0;
        MedienFilter neuMedien = medium -> medium.getErscheinungsjahr().getValue() >= 2025;
        MedienFilter nurFilme  = medium -> medium instanceof Film;

        System.out.println("Beliebte Medien: "        + verwaltung.filtere(beliebt));
        System.out.println("Neue Medien: "            + verwaltung.filtere(neuMedien));
        System.out.println("Nur Filme: "              + verwaltung.filtere(nurFilme));

        System.out.println("Anzahl beliebte Medien: " + verwaltung.zaehleMitFilter(beliebt));
        System.out.println("Anzahl neue Medien: "     + verwaltung.zaehleMitFilter(neuMedien));
        System.out.println("Anzahl Filme: "           + verwaltung.zaehleMitFilter(nurFilme));

        verwaltung.writePopularToCsv(beliebt);
        verwaltung.writeFilmeToCsv(nurFilme);
    }
}