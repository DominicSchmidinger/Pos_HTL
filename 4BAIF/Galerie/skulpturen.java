package Galerie;

public class skulpturen extends kunstwerke {
    private String material;
    private int hoehe;
    private boolean seltenheit;
    public skulpturen(String kuenstler, String titel, int laenge, int breite, double ekPreis, boolean verkauft) {
        super(kuenstler, titel, laenge, breite, ekPreis, verkauft);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material.isEmpty()) {
            throw new NullPointerException("des dearf ned null sein mia verkaufen keine luft");
        }
        this.material = material;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) {
        if (hoehe <= 0) {
            throw new IllegalArgumentException("nein");
        }
        this.hoehe = hoehe;
    }

    public boolean isSeltenheit() {
        return seltenheit;
    }

    public void setSeltenheit(boolean seltenheit) {
        this.seltenheit = seltenheit;
    }

    @Override
    public double berechneVKWert() {
        int wert = 0;
        if (isSeltenheit() == true) {
            wert *= 1.5;
        }else wert *= 1.25;

        return wert;
    }

}
