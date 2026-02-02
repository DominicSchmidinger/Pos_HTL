package spengergasse.at;

public class Main {
    public static void main(String[] args) {
        // KinoSaal mit 5 Reihen und 10 Plätzen pro Reihe
        KinoSaal saal = new KinoSaal(5, 10);

        System.out.println("Anfangs-Sitzplan:");
        System.out.println(saal.gibSitzplanaus());

        // Prüfen, ob eine Gruppe von 4 sitzen kann
        int gruppe = 4;
        if (saal.kannGruppenSitzen(gruppe)) {
            System.out.println("Eine Gruppe von " + gruppe + " kann sitzen.");
        } else {
            System.out.println("Keine zusammenhängenden Plätze für " + gruppe + " vorhanden.");
        }

        // Plätze reservieren
        if (saal.reservierungGruppen(gruppe)) {
            System.out.println("Plätze für Gruppe von " + gruppe + " wurden reserviert.");
        } else {
            System.out.println("Reservierung für Gruppe von " + gruppe + " fehlgeschlagen.");
        }

        // Sitzplan nach Reservierung
        System.out.println("Sitzplan nach Reservierung:");
        System.out.println(saal.gibSitzplanaus());

        // Weitere Tests
        gruppe = 7;
        if (saal.kannGruppenSitzen(gruppe)) {
            System.out.println("Eine Gruppe von " + gruppe + " kann sitzen.");
        } else {
            System.out.println("Keine zusammenhängenden Plätze für " + gruppe + " vorhanden.");
        }
    }
}
