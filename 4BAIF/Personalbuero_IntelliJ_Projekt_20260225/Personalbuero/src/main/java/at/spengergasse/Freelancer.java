package at.spengergasse;

import java.time.Year;

public class Freelancer extends Mitarbeiter{
    private double stundenSatz;
    private int stunden;

    public Freelancer(String name, Year gebJahr, Year eintrJahr, double stundenSatz, int stunden) {
        super(name, gebJahr, eintrJahr);
        setStundenSatz(stundenSatz);
        setStunden(stunden);
    }

    public Freelancer() {
        super("Anna", Year.of(2001), Year.now());
        setStunden(10);
        setStundenSatz(100.0);
    }

    public double getStundenSatz() {
        return stundenSatz;
    }

    public void setStundenSatz(double stundenSatz) {
        if (stundenSatz >= 0.0) {
            this.stundenSatz = stundenSatz;
        } else {
            System.out.println("Fehler: Wert von stundenSatz darf nicht negativ sein");
        }
    }

    public int getStunden() {
        return stunden;
    }

    public void setStunden(int stunden) {
        if (stunden >= 0) {
            this.stunden = stunden;
        } else {
            System.out.println("Fehler: Wert von stunden darf nicht negativ sein");
        }
    }

    @Override
    public double berechneGehalt() {
        return stunden * stundenSatz;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Freelancer that = (Freelancer) o;
        return Double.compare(stundenSatz, that.stundenSatz) == 0 && stunden == that.stunden;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Double.hashCode(stundenSatz);
        result = 31 * result + stunden;
        return result;
    }

    @Override
    public String toString() {
        return "Freelancer: " +
                super.toString() + // für alle gemeinsamen Informationen aus der Superklasse Mitarbeiter
                ", Stundensatz: " + stundenSatz +
                ", Stunden: " + stunden;
    }








}
