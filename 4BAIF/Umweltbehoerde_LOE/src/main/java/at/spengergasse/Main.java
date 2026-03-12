package at.spengergasse;

import java.time.Year;
import java.util.List;

public class Main {

    static void main(String[] args) {

        // Aufruf einer statischen Meethode:
        testRemoveAlleStationenAelterAls();

        //Messstation messstation = new Messstation();

        Luftmessstation luftmessstation = new Luftmessstation("Wien", Year.now().minusYears(2), 21.0, 55.5);
        Laermmessstation laermmessstation = new Laermmessstation("Eisenstadt", Year.now(), 50.0, 30);
        Wetterstation wetterstation = new Wetterstation("Graz", Year.now().minusYears(10), 21, 12);
        System.out.println(luftmessstation.getStationTyp());
        System.out.println(laermmessstation.getStationTyp());
        System.out.println(wetterstation.getStationTyp());

        UmweltBehoerde umweltBehoerde = new UmweltBehoerde();
        System.out.println(umweltBehoerde.addMessstation(null));
        System.out.println(umweltBehoerde.addMessstation(luftmessstation));
        System.out.println(umweltBehoerde.addMessstation(luftmessstation));
        System.out.println(umweltBehoerde.addMessstation(laermmessstation));
        System.out.println(umweltBehoerde.addMessstation(wetterstation));
        System.out.println(umweltBehoerde);

        System.out.println(umweltBehoerde.berechneDurchschnittMesswert());
        System.out.println("Luft: " + umweltBehoerde.berechneDurchschnittLuftmessstationen());
        System.out.println("Lärm: " + umweltBehoerde.berechneDurchschnittLaermmessstationen());
        System.out.println("Wetter: " + umweltBehoerde.berechneDurchschnittWetterstation());

        System.out.println("Anzahl zu hoher Wert: " + umweltBehoerde.zaehleAlleStationenMitZuHohemWert(50));
        System.out.println("Anzahl zu hoher Wert: " + umweltBehoerde.zaehleAlleStationenMitZuHohemWert(0));
        umweltBehoerde.zaehleStationstypen();
        umweltBehoerde.sortiereMessstationen();
        umweltBehoerde.zeigeAlleStationen();

        System.out.println(umweltBehoerde.removeMessstation(laermmessstation));
        System.out.println(umweltBehoerde);

        System.out.println("Nur Lärmstationen: ");
        umweltBehoerde.zeigeNurLaermessstationen();
        System.out.println("Nur Luftstationen: ");
        umweltBehoerde.zeigeNurLuftmessstationen();
        System.out.println("Alle Stationen: ");
        umweltBehoerde.zeigeAlleStationen();

        System.out.println(umweltBehoerde.removeErsteStationNachStandort("Wien"));
        System.out.println(umweltBehoerde.removeErsteStationNachStandort("gibtEsNicht"));
        System.out.println(umweltBehoerde);
    }

    private static void testRemoveAlleStationenAelterAls() {
        Luftmessstation luftmessstation = new Luftmessstation("Wien", Year.now().minusYears(2), 21.0, 55.5);
        Laermmessstation laermmessstation = new Laermmessstation("Eisenstadt", Year.now(), 50.0, 30);
        Wetterstation wetterstation = new Wetterstation("Graz", Year.now().minusYears(10), 21, 12);

        UmweltBehoerde umweltBehoerde = new UmweltBehoerde();

        System.out.println(umweltBehoerde.addMessstation(luftmessstation));
        System.out.println(umweltBehoerde.addMessstation(laermmessstation));
        System.out.println(umweltBehoerde.addMessstation(wetterstation));
        System.out.println(umweltBehoerde);

        List<Messstation> messstationenEntfernt = umweltBehoerde.removeAlleStationenAelterAls(1);
        System.out.println(messstationenEntfernt.size()); // 2
        System.out.println("Entfernte Messstationen: ");
        for(Messstation m : messstationenEntfernt) {
            System.out.println(m);
        }
        System.out.println();
        System.out.println("Messstationen in der Umweltbehörde: ");
        System.out.println(umweltBehoerde);
    }
}
