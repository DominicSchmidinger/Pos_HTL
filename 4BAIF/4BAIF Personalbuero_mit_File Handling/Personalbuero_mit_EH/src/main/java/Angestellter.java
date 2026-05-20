import java.time.Year;

public class Angestellter extends Mitarbeiter{

    public Angestellter(String name, Year gebJahr, Year eintrJahr) throws PersonalException {
        super(name, gebJahr, eintrJahr);
    }

    @Override
    public double berechneGehalt() {
        double grundgehalt = 1500.0;
        double proJahr = 50d;
        return grundgehalt + proJahr*berechneDienstalter();
    }

    @Override
    public String toCsvString() {
        return super.toCsvString();
    }

    @Override
    public String toString() {
        return "Angestellter: " + super.toString();
    }
}
