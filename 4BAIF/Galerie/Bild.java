package Galerie;

public class Bild extends kunstwerke {
   private boolean sehrGefragt;
    public Bild(String kuenstler, String titel, int laenge, int breite, double ekPreis, boolean verkauft, boolean sehrGefragt) {
        super(kuenstler, titel, laenge, breite, ekPreis, verkauft);
        this.sehrGefragt = sehrGefragt;
    }

    public boolean isSehrGefragt() {
        return sehrGefragt;
    }

    public void setSehrGefragt(boolean sehrGefragt) {
        this.sehrGefragt = sehrGefragt;
    }

    @Override
    public double berechneVKWert() {
        double wert = getEkPreis();

        if (this.isSehrGefragt()) {
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
