package at.spengergasse;

import java.time.Year;

public class Arzt extends Mitarbeiter {

    private int wochenStunden;
    private double fixum;

    public Arzt(String name, Year gebJahr, Year eintrJahr, int wochenStunden, double fixum) {
        super(name, gebJahr, eintrJahr);
        setWochenStunden(wochenStunden);
        setFixum(fixum);
    }

    public int getWochenStunden() {
        return wochenStunden;
    }

    public void setWochenStunden(int wochenStunden) {
        if (wochenStunden < 0 || wochenStunden > 168) { // Annahme: max. 24*7 = 168 Stunden/Woche
            System.out.println("Fehler: darf nicht negativ oder größer 7*24 sein");
        } else {
            this.wochenStunden = wochenStunden;
        }
    }

    public double getFixum() {
        return fixum;
    }

    public void setFixum(double fixum) {
        if(fixum < 0.0) {  // fail-fast
            System.out.println("Fehler: muss 0 oder größer sein");
        } else {
            this.fixum = fixum;
        }
    }

    @Override
    public double berechneGehalt() {
        return fixum;
    }

    public double berechneStundensatz() {
        if(wochenStunden > 0) { // happy-path oder fail-fast
            return fixum / wochenStunden; // wochenStunden könnte 0 sein
        } else {
            return -99.0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Arzt arzt = (Arzt) o;
        return wochenStunden == arzt.wochenStunden && Double.compare(fixum, arzt.fixum) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + wochenStunden;
        result = 31 * result + Double.hashCode(fixum);
        return result;
    }

    @Override
    public String toString() {
        return "Arzt: " +
                super.toString() +
                ", Wochenstunden: " + wochenStunden +
                ", Fixum: " + fixum;
    }
}
