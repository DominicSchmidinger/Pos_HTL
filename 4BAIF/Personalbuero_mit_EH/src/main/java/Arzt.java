import java.time.Year;

public class Arzt extends Mitarbeiter{
    private int wochenStunden;
    private double fixum;

    public Arzt(String name, Year gebJahr, Year eintrJahr, int wochenStunden, double fixum) throws PersonalException {
        super(name, gebJahr, eintrJahr);
        setWochenStunden(wochenStunden);
        setFixum(fixum);
    }

    public int getWochenStunden() {
        return wochenStunden;
    }

    public void setWochenStunden(int wochenStunden) throws PersonalException {
        if (wochenStunden > 0) {
            this.wochenStunden = wochenStunden;
        } else {
            //System.out.println("Fehler: muss positiv sein");
            throw new PersonalException("Fehler: muss positiv sein");
        }
    }

    public double getFixum() {
        return fixum;
    }

    public void setFixum(double fixum) throws PersonalException {
        if (fixum > 0.0) {
            this.fixum = fixum;
        } else {
            //System.out.println("Fehler: muss positiv sein");
            throw new PersonalException("Fehler: muss positiv sein");
        }
    }

    @Override
    public double berechneGehalt() {
        return fixum;
    }

    public double berechneStundensatz() {
        if (wochenStunden > 0) {
            return fixum / wochenStunden;
        } else {
            return -99.0; // Fehlercode
        }
    }

    @Override
    public String toString() {
        return "Arzt: " + super.toString();
    }







}
