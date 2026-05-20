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
        System.out.println("== CSV Import/Export ==");
        Medienverwaltung verwaltung = new Medienverwaltung();
        verwaltung.readFromCsv();

        System.out.println(verwaltung);
        verwaltung.writeToCsv();
        verwaltung.writeReversedToCsv();
    }

    private static void testLambdaFilter() {
        System.out.println("== Lambda und Filter ==");
        Medienverwaltung verwaltung = new Medienverwaltung();
        verwaltung.readFromCsv();

        // TODO: Lambda-Ausdruck für Medien mit Beliebtheit größer als 50.0 erstellen
        // TODO: Lambda-Ausdruck für Medien ab dem Jahr 2025 erstellen
        // TODO: Lambda-Ausdruck für alle Filme erstellen
        // TODO: filtere(...) mit mindestens einem Lambda-Ausdruck testen
        // TODO: zaehleMitFilter(...) mit mindestens einem Lambda-Ausdruck testen
        // TODO: writePopularToCsv(...) und writeFilmeToCsv(...) testen
    }
}
