package at.spengergasse;

import java.time.Year;

public class Main {
    public static void main(String[] args) {
            /*
            Mitarbeiter mitarbeiter1 = new Mitarbeiter("Anna", Year.of(2000), Year.now());
            PersonalbueroKlein personalbueroKlein = new PersonalbueroKlein();

            System.out.println(personalbueroKlein.aufnehmen(mitarbeiter1));  // true
            System.out.println(personalbueroKlein.getAnzahl());  // 1

            Mitarbeiter mitarbeiter2 = new Mitarbeiter("Bert", Year.of(2001), Year.now());
            System.out.println(personalbueroKlein.aufnehmen(mitarbeiter2));  // true
            System.out.println(personalbueroKlein.getAnzahl());  // 2

            Mitarbeiter mitarbeiter3 = new Mitarbeiter("Cölestine", Year.of(2002), Year.now());
            System.out.println(personalbueroKlein.aufnehmen(mitarbeiter3));  // true
            System.out.println(personalbueroKlein.getAnzahl());  // 3

            Mitarbeiter mitarbeiter4 = new Mitarbeiter("Daniel", Year.of(2003), Year.now());
            System.out.println(personalbueroKlein.aufnehmen(mitarbeiter4));  // false
            System.out.println(personalbueroKlein.getAnzahl());  // 3


             */

            Angestellter angestellter = new Angestellter("Franz", Year.of(2002), Year.now());
            System.out.println(angestellter);
            System.out.println(angestellter.berechneAlter());

            Freelancer freelancerMe = new Freelancer("Rainer", Year.of(1971), Year.now(), 100.0, 10);
            System.out.println(freelancerMe);
            System.out.println();

            Freelancer freelancer = new Freelancer();
            System.out.println(freelancer);
            System.out.println();
    }
}
