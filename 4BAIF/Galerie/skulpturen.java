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

    public void setMaterial(String material) throws GalerieExceptions {
        if (material.isEmpty()) {
            throw new GalerieExceptions("des dearf ned null sein mia verkaufen keine luft");
        }
        this.material = material;
    }

    public int getHoehe() {
        return hoehe;
    }

    public void setHoehe(int hoehe) throws GalerieExceptions {
        if (hoehe <= 0) {
            throw new GalerieExceptions("nein");
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
        } else wert *= 1.25;

        return wert;
    }

    @Override
    public String toString() {
        return
                super.toString() + "-" + material + "x" + hoehe + " " + seltenheit;
    }
}
