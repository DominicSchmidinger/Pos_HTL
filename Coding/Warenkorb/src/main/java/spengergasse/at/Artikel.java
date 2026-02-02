package spengergasse.at;

public class Artikel {

    private String Brot;
    private double preis;
    private int menge;


    public Artikel (String Brot, double preis, int menge){
        this.Brot = Brot;
        this.preis = preis;
        this.menge = menge;

    }

    public String getBrot() {
        return this.Brot;
    }

    public double getPreis() {
        return this.preis;
    }

    public int getMenge() {
        return this.menge;
    }

    public void setPreis(double preis) {
        if (preis < 0.1){
            throw new IllegalArgumentException("gratis gibts nicht weil Kapitalismuss und so");
        }
        this.preis = preis;
    }

    public void setMenge(int menge) {
        if (menge <= 0){
            throw new IllegalArgumentException("warum?");
        }
        this.menge = menge;
    }

    public double getGesamtpreis(){
        return preis * menge;
    }

    public String toString() {
       return "Artikel:" + Brot + ", Preis:" + preis + ", Menge:" + menge + ", Gesamtpreis:" + getGesamtpreis();
    }


}
