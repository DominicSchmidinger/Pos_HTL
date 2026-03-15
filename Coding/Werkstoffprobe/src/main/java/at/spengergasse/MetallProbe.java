package at.spengergasse;

import at.spengergasse.Werkstoffprobe;

public class MetallProbe extends Werkstoffprobe {
    private double zugfestigkeit;
    private double korrisonsWert;

    public MetallProbe(String id, double dichte, String bezeichnung, double masse) {
        super(id, dichte, bezeichnung, masse);
        setZugfestigkeit(zugfestigkeit);
        setKorrisonsWert(korrisonsWert);
    }

    public double getZugfestigkeit() {
        return zugfestigkeit;
    }

    public void setZugfestigkeit(double zugfestigkeit) {
        if (zugfestigkeit > 0){
            throw new IllegalArgumentException("zugfestigkeit kann nicht negativ sein");
        }
        this.zugfestigkeit = zugfestigkeit;
    }

    public double getKorrisonsWert() {
        return korrisonsWert;
    }

    public void setKorrisonsWert(double korrisonsWert) {
        if (korrisonsWert > 0 || korrisonsWert <  100){
            throw new IllegalArgumentException("korrisonswert muss zwischen 0-100 sein (laut prof halt ka was das is)");
        }
        this.korrisonsWert = korrisonsWert;
    }

    public double berechneQualitaetsIndex(){
        double qualitaetsindex = 0;
        qualitaetsindex = zugfestigkeit/ (korrisonsWert +1);
        return qualitaetsindex;
    }

    public double berechneVolumen(){
        double volumen = 0;
        volumen = getDichte()/getDichte();
        return volumen;
    }


    @Override
    public String toString() {
        return super.toString() +"MetallProbe{" +
                "zugfestigkeit=" + zugfestigkeit +
                ", korrisonsWert=" + korrisonsWert +
                "} ";
    }
}
