package at.spengergasse;
public class Main {
    static void main() {
        Notizen notizen = new Notizen();
        // abgesichter Block
        try {
            System.out.println(notizen.notizHinzufuegen("Brot einkaufen", null));  // null
            System.out.println(notizen.notizHinzufuegen("Video über Java-Streams ansehen", "Hobby"));  // null
            System.out.println(notizen.notizHinzufuegen("Geschenk für Petra besorgen", "Familie"));  // null
        } catch (NotizenException e) {
            System.out.println("Unerwarteter Programmfehler: " + e.getMessage());
        }

        notizen.notizenAusgebenOhneKategorie();
        System.out.println();
        notizen.kategorienAusgebenOhneNotizen();
        System.out.println(notizen.anzahlNotizen()); // 3
        notizen.ausgeben();
        System.out.println();

        // Kommt eine Exception? Ja, sicher, weil wir null übergeben
        try {
            System.out.println(notizen.notizHinzufuegen(null, "Familie"));
            System.out.println("FEHLER: Dieser Text darf nicht mehr zu sehen sein!!!");
        } catch (NotizenException e) {
            System.out.println("OK: Erwartetete Exception: " + e.getMessage());
        }
//        } finally {
//            System.out.println("Hier bin ich bei den Aufräumarbeiten");
//        }
        System.out.println("------------- Test Ende -------------------");

    }
}
