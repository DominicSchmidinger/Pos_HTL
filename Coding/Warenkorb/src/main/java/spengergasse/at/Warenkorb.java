package spengergasse.at;

import java.util.ArrayList;


public class Warenkorb {
    private ArrayList<Artikel> waren;

    public Warenkorb() {
        this.waren = new ArrayList<>();
    }


    public void getArtikel(String Brot, double preis) {
        if (waren.contains(Brot)) {
            System.out.println(Brot + ": " + preis);
        }

    }


    public Artikel aufnehmen(Artikel ar) {
        if (ar == null) {
            return null;
        }
        if (waren.contains(ar)) {
            ar.setMenge(ar.getMenge() + 1);
        }
        else waren.add(ar);
        return ar;
    }

    public boolean istDrinnen(Artikel artikel) {
        if (waren.contains(artikel)) {
            return true;
        }
        return false;
    }

    public double berechneSumme() {
        int gesamtpreis = 0;

        for (int i = 0; i < waren.size(); i++) {
            gesamtpreis += waren.get(i).getPreis();
        }
        return gesamtpreis;
    }
    public double durchschnittspreis(){
        double durchschnittspreis = 0;
       durchschnittspreis = berechneSumme()/ mengeArtikel();
        return durchschnittspreis;
    }
    public int mengeArtikel(){
        int menge = 0;

        for (int i = 0; i < waren.size(); i++){
            menge += waren.get(i).getMenge();
        }
        return menge;
    }
    public int anzahlArtikel(){
        return waren.size();
    }

    @Override
    public String toString() {
        return "Warenkorb{" +
                "waren=" + waren +
                '}';
    }
}