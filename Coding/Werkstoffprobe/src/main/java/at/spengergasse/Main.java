package at.spengergasse;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        try {
            // Erstellen einer MetallProbe mit gültigen Werten laut deiner Angabe
            MetallProbe probe = new MetallProbe("WZ-201", 7.85, "Stahl S355", 10.0, 510.0, 12.0);
            PolymerProbe probe2 = new PolymerProbe("WZ_305", 1.2, "Polycarbonat", 4.5, 145);
            KeramikProbe probe3 = new KeramikProbe("WZ-410X", 3.9, "Aluminiumoxid", 6.2);

            System.out.println("--- Werkstoffzentrum Test ---");
            // Die toString() Methode gibt alles inklusive Volumen und Index aus
            System.out.println(probe.toString());
            System.out.println(probe2.toString());
            System.out.println(probe3.toString());

        } catch (IllegalArgumentException e) {
            System.err.println("Fehler bei der Validierung: " + e.getMessage());
        }
    }
}
