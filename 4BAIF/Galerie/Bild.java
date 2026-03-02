package Galerie;

public class Bild extends kunstwerke {
   private boolean sehrGefragt;
    public Bild(String kuenstler, String titel, int laenge, int breite, double ekPreis, boolean verkauft, boolean sehrGefragt) {
        super(kuenstler, titel, laenge, breite, ekPreis, verkauft);
        setsehrGefragt(sehrGefragt);
    }

    public void setsehrGefragt(boolean sehrGefragt) {
        this.sehrGefragt = sehrGefragt;
    }

    public boolean isSehrGefragt() {
        return sehrGefragt;
    }

    @Override
    public double berechneVKWert() {
        double wert = getEkPreis();

        if (sehrGefragt == true) {
            wert *= 1.5;
        }
        else  {
            wert *= 1.25;
        }
        return wert;
    }

    @Override
    public String toString() {
        return
        super.toString() + "-" + getLaenge() + "x" + getBreite();

    }
}
