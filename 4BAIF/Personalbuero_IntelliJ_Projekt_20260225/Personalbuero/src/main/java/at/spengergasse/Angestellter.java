package at.spengergasse;

import java.time.Year;

public class Angestellter extends Mitarbeiter{
    public Angestellter(String name, Year gebJahr, Year eintrJahr) {
        super(name, gebJahr, eintrJahr);
    }

    @Override
    public double berechneGehalt() {
        return 1500.0 + 50.0 * berechneDienstalter();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Angestellter: ");
        sb.append(super.toString());  // alle Infos vom Mitarbeiter hinzufügen
        return sb.toString();
    }
}
