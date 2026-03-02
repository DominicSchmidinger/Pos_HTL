package Galerie;

public abstract class kunstwerke {
    private final String kuenstler;
    private String titel;
    private int laenge;
    private int breite;
    private double ekPreis;
    private boolean verkauft;

    public kunstwerke(String kuenstler, String titel, int laenge, int breite, double ekPreis, boolean verkauft) {
        this.kuenstler = kuenstler;
        this.titel = titel;
        this.laenge = laenge;
        this.breite = breite;
        this.ekPreis = ekPreis;
        this.verkauft = false;
    }



    public String getKuenstler() {
        return kuenstler;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public double getEkPreis() {
        return ekPreis;
    }

    public void setEkPreis(double ekPreis) {
        this.ekPreis = ekPreis;
    }

    public boolean isVerkauft() {
        return verkauft;
    }

    public void setVerkauft(boolean verkauft) {
        this.verkauft = verkauft;
    }

    public abstract double berechneVKWert();

    @Override
    public String toString() {
        return  kuenstler + "-" + titel + "-" + ekPreis + "-" + verkauft;
    }
}
