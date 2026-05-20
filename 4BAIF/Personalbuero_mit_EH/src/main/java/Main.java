import java.time.Year;

public class Main {
    public static void main(String[] args) {

        testZaehleAngestellte();
        testGetMitarbeiter();

        //testAufnehmenUndDurchschnittPersonalbueroKlein();
        //testToStringPersonalbuero();
        //testKuendigenPersonalbuero(); //neu
        //Angestellter angestellter = new Angestellter("Hubert", Year.of(2000), Year.now());
        //System.out.println(angestellter);
     }

    private static void testGetMitarbeiter() {
        try {
            Personalbuero personalbuero = new Personalbuero();
            System.out.println(personalbuero.getMitarbeiter(0)); // null
            Angestellter angestellter = new Angestellter("Anton", Year.of(2000), Year.now());
            personalbuero.aufnehmen(angestellter);
            System.out.println(personalbuero); // 1
            System.out.println();
            System.out.println(personalbuero.getMitarbeiter(0));
            System.out.println();
        } catch (PersonalException e) {
            System.out.println("FEHLER: unerwartete Exception: " + e.getMessage());
        }
    }

    private static void testZaehleAngestellte() {
        try {
            Angestellter angestellter = new Angestellter("Hubert", Year.of(2000), Year.now());
            Personalbuero personalbuero = new Personalbuero();
            System.out.println(personalbuero.zaehleAngestellte()); // -99
            personalbuero.aufnehmen(angestellter);
            System.out.println(personalbuero.zaehleAngestellte()); // 1
            System.out.println();
        } catch (PersonalException e) {
            System.out.println("FEHLER: unerwartete Exception: " + e.getMessage());
        }
    }

    /*
    public static void testKuendigenPersonalbuero() {
        Personalbuero p = new Personalbuero();
        p.aufnehmen(new Mitarbeiter("Anton", Year.of(2000), Year.now()));
        p.aufnehmen(new Mitarbeiter("Berta", Year.of(2001), Year.now()));
        System.out.println(p); // Anton, Berta
        System.out.println();
        System.out.println("-------------------------");
        System.out.println(p.kuendigen(null)); // false
        System.out.println(p.kuendigen("Caesar")); // false
        System.out.println(p.kuendigen("Anton")); // true
        System.out.println("-------------------------");
        System.out.println(p); // Berta
        System.out.println();
    }


    public static void testToStringPersonalbuero() {
        Personalbuero p = new Personalbuero();
        System.out.println(p);
        System.out.println();
        System.out.println("-------------------------");
        p.aufnehmen(new Mitarbeiter("Anton", Year.of(2000), Year.now()));
        p.aufnehmen(new Mitarbeiter("Berta", Year.of(2001), Year.now()));
        System.out.println(p); // Anton, Berta
        System.out.println();
    }

    public static void testAufnehmenUndDurchschnittPersonalbueroKlein()
     {
         // Test aufnehmen
         // given
         PersonalbueroKlein personalbueroKlein = new PersonalbueroKlein();
         //Mitarbeiter mitarbeiter = new Mitarbeiter("Anton", Year.parse("zweitausend"), Year.parse("2026"));
         Mitarbeiter mitarbeiter = new Mitarbeiter("Anton", Year.of(2000), Year.now());
         // ACHTUNG bei Year.parse("2000") -> Exceptions möglich
         // when
         personalbueroKlein.aufnehmen(mitarbeiter);
         System.out.println(personalbueroKlein.berechneGehaltsumme());
         // then
         // 1500.0

         System.out.println("-----------------------------------");
         // testDurchschnittsalter
         System.out.println(personalbueroKlein.berechneDurchschnittsalter()); // 26/1 = 26.0
         Mitarbeiter mitarbeiter1 = new Mitarbeiter("Berta", Year.of(2001), Year.now());
         personalbueroKlein.aufnehmen(mitarbeiter1);
         System.out.println(personalbueroKlein.berechneDurchschnittsalter()); // 26+25 = 51/2 = 25.5

         System.out.println("-----------------------------------");
         personalbueroKlein.aufnehmen(new Mitarbeiter("Cäsar", Year.of(2002), Year.now()));
         System.out.println(personalbueroKlein);
         System.out.println(personalbueroKlein.getAnzahl());
         System.out.println();
         System.out.println("-----------------------------------");

         personalbueroKlein.aufnehmen(new Mitarbeiter("Doris", Year.of(2003), Year.now()));
         System.out.println(personalbueroKlein);
     }

 */
}
